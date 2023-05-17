package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.exception.ConflictException;
import com.example.pisioconf_backend.exception.ForbiddenException;
import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Korisnik;
import com.example.pisioconf_backend.models.dto.LoginResponse;
import com.example.pisioconf_backend.models.entities.KorisnikEntity;
import com.example.pisioconf_backend.models.enums.Role;
import com.example.pisioconf_backend.models.enums.UserStatus;
import com.example.pisioconf_backend.models.requests.*;
import com.example.pisioconf_backend.repositories.KorisnikRepository;
import com.example.pisioconf_backend.services.EmailService;
import com.example.pisioconf_backend.services.KorisnikService;
import com.example.pisioconf_backend.util.LoggingUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KorisnikImplService implements KorisnikService {

    public final KorisnikRepository korisnikRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    @Value("${authorization.default.username:}")
    private String defaultUsername;
    @Value("${authorization.default.password:}")
    private String defaultPassword;
    @Value("${authorization.default.first-name:}")
    private String defaultFirstName;
    @Value("${authorization.default.email:}")
    private String defaultEmail;


    @PersistenceContext
    private EntityManager manager;

    public KorisnikImplService(KorisnikRepository korisnikRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, EmailService emailService) {
        this.korisnikRepository = korisnikRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
    }

    @PostConstruct
    public void postConstruct() {

        if (korisnikRepository.count() == 0) {
            KorisnikEntity userEntity = new KorisnikEntity();
            userEntity.setUsername(defaultUsername);
            userEntity.setPassword(passwordEncoder.encode(defaultPassword));
            userEntity.setEmail(defaultEmail);
            userEntity.setNaziv(defaultFirstName);
            userEntity.setStatus(KorisnikEntity.Status.ACTIVE);
            userEntity.setRola(Role.ADMIN);
            korisnikRepository.saveAndFlush(userEntity);
        }
    }


    @Override
    public KorisnikEntity findById(Integer id) {
        return korisnikRepository.findById(id).get();
    }

    @Override
    public List<Konferencija> getKonferencijeZaModeratora(Integer id) {
        return korisnikRepository.getAllByKorisnikByModeratorId(id).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());
    }

    @Override
    public List<Konferencija> getKonferencijeZaOrganizatora(Integer id) {
        return korisnikRepository.getAllByKorisnikByOrganizatorId(id).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());
    }

    @Override
    public LoginResponse findById(Integer id, Class<LoginResponse> response) throws NotFoundException {
        return modelMapper.map(korisnikRepository.findById(id).orElseThrow(NotFoundException::new), LoginResponse.class);
    }

    public void signUp(SignUpRequest request) {
        if (korisnikRepository.existsByUsername(request.getUsername()))
            throw new ConflictException();
        KorisnikEntity entity = modelMapper.map(request, KorisnikEntity.class);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setStatus(KorisnikEntity.Status.REQUESTED);
        entity.setRola(request.getRola());
        Korisnik user = insert(entity, Korisnik.class);

    }

    @Override
    public void changeStatus(Integer userId, ChangeStatusRequest request) throws Exception {
        KorisnikEntity.Status status = korisnikRepository.findById(userId).get().getStatus();
        KorisnikEntity entity = korisnikRepository.findById(userId).get();
        if (status.toString().equals(UserStatus.REQUESTED.toString()) && UserStatus.ACTIVE.equals(request.getStatus())) {
            entity.setStatus(KorisnikEntity.Status.ACTIVE);
             emailService.sendMailApproved(entity.getEmail());

        }
        if (status.toString().equals(UserStatus.BLOCKED.toString()) && UserStatus.ACTIVE.equals(request.getStatus())) {
            entity.setStatus(KorisnikEntity.Status.ACTIVE);
             emailService.sendMailApproved(entity.getEmail());
        }
        if (status.toString().equals(UserStatus.REQUESTED.toString()) && UserStatus.BLOCKED.equals(request.getStatus())) {
            entity.setStatus(KorisnikEntity.Status.BLOCKED);
             emailService.sendSimpleMailNotApproved(entity.getEmail());
        }
        if (UserStatus.REQUESTED.equals(request.getStatus())) {
            throw new ForbiddenException();
        }
        entity.setStatus(modelMapper.map(request.getStatus(), KorisnikEntity.Status.class));
        korisnikRepository.saveAndFlush(entity);
    }

    @Override
    public void changeRole(Integer userId, ChangeRoleRequest request) {
        KorisnikEntity entity = korisnikRepository.findById(userId).get();

        entity.setRola(request.getRole());
        korisnikRepository.saveAndFlush(entity);
    }

    @Override
    public Korisnik update(Integer id, UserUpdateRequest user) throws Exception {
        if (korisnikRepository.existsByUsernameAndIdNot(user.getIme(), id))
            throw new ConflictException();
        KorisnikEntity entity = korisnikRepository.findById(id).get();
        if (user.getIme() != null && user.getIme().length() > 0 && !user.getIme().equals(entity.getNaziv())) {
            entity.setNaziv(user.getIme());
        }
        if (user.getKorisnickoIme() != null && user.getKorisnickoIme().length() > 0 &&
                !user.getKorisnickoIme().equals(entity.getUsername())) {
            entity.setUsername(user.getKorisnickoIme());
        }
        if (user.getEmail() != null && user.getEmail().length() > 0 && !user.getEmail().equals(entity.getEmail())) {
            entity.setEmail(user.getEmail());
        }
        if (user.getLozinka() != null && user.getLozinka().length() > 0 && !user.getLozinka().equals(entity.getPassword())) {
            entity.setPassword(passwordEncoder.encode(user.getLozinka()));
        }
        entity.setId(id);
        entity = korisnikRepository.saveAndFlush(entity);
        manager.refresh(entity);
        return modelMapper.map(entity, Korisnik.class);

    }

    @Override
    public void updatePassword(Integer id, ChangePasswordRequest request) throws Exception {
        KorisnikEntity entity = korisnikRepository.findById(id).get();
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    entity.getUsername(), request.getTrenutnaLozinka()
                            )
                    );

            if (request.getPassword() != null && request.getPassword().length() > 0 && !request.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(request.getPassword()));
            }
            entity.setId(id);
           // entity.setResetToken(null);
            korisnikRepository.save(entity);
        } catch (Exception ex) {
            LoggingUtil.logException(ex, getClass());
            throw new Exception("Trenutna lozinka koja je unesena nije ispravna!");
        }
    }

    @Override
    public Korisnik insert(KorisnikEntity korisnikEntity, Class<Korisnik> korisnik) throws NotFoundException {
        //KorisnikEntity entity=mapper.map(korisnik,entityClass);
        korisnikEntity.setId(null);
        korisnikEntity = korisnikRepository.saveAndFlush(korisnikEntity);
        manager.refresh(korisnikEntity);
        return modelMapper.map(korisnikEntity, korisnik);
    }

}
