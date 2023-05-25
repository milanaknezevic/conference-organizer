package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.models.dto.*;
import com.example.pisioconf_backend.models.entities.*;
import com.example.pisioconf_backend.repositories.*;
import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;
import com.example.pisioconf_backend.services.KonferencijaService;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KonferencijaImplService implements KonferencijaService {
    private final KonferencijaRepository konferencijaRepository;
    private final PosjetilacRepository posjetilacRepository;
    private final KorisnikRepository korisnikRepository;
    private final ModelMapper modelMapper;
    private final LokacijaRepository lokacijaRepository;
    private final OcjenaRepository ocjenaRepository;
    private final DogadjajRepository dogadjajRepository;

    public KonferencijaImplService(KonferencijaRepository konferencijaRepository, PosjetilacRepository posjetilacRepository, KorisnikRepository korisnikRepository, ModelMapper modelMapper,
                                   LokacijaRepository lokacijaRepository,
                                   OcjenaRepository ocjenaRepository, DogadjajRepository dogadjajRepository) {
        this.konferencijaRepository = konferencijaRepository;
        this.posjetilacRepository = posjetilacRepository;
        this.korisnikRepository = korisnikRepository;

        this.modelMapper = modelMapper;
        this.lokacijaRepository = lokacijaRepository;
        this.ocjenaRepository = ocjenaRepository;
        this.dogadjajRepository = dogadjajRepository;
    }


    @Override
    public List<Konferencija> findAll() {
        return konferencijaRepository.findAll().stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());
    }

    @Override
    public List<Konferencija> findAllByModeratorId(Integer id) throws NotFoundException {
        return konferencijaRepository.findAllKonferencijeByModeratorId(id).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());
    }


    @Override
    public List<Konferencija> getAllKonferencijeByLocationId(Integer id) {
        return konferencijaRepository.getAllByLokgacijaByLokacijaId_Id(id).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());
    }

    @Override
    public Konferencija insert(KonferencijaRequest konferencijaRequest) throws NotFoundException {
        KorisnikEntity organizatorEnttity = korisnikRepository.findById(konferencijaRequest.getOrganizatorId()).get();
        LokacijaEntity lokacijaEntity = lokacijaRepository.findById(konferencijaRequest.getLokacijaId()).get();

        KonferencijaEntity konferencijaEntity = modelMapper.map(konferencijaRequest, KonferencijaEntity.class);

        konferencijaEntity.setKorisnik(organizatorEnttity);
        konferencijaEntity.setLokacija(lokacijaEntity);

        konferencijaEntity.setId(null);

        konferencijaEntity = konferencijaRepository.saveAndFlush(konferencijaEntity);
        return findById(konferencijaEntity.getId());
    }

    @Override
    public Konferencija update(Integer id, KonferencijaRequest konferencijaRequest) throws NotFoundException {
        KonferencijaEntity konferencijaEntity = konferencijaRepository.findById(id).get(); // modelMapper.map(konferencijaRequest, KonferencijaEntity.class);
        if (konferencijaRequest.getStartTime() != null) {
            konferencijaEntity.setStartTime(konferencijaRequest.getStartTime());
        }
        if (konferencijaRequest.getEndTime() != null) {
            konferencijaEntity.setEndTime(konferencijaRequest.getEndTime());
        }
        if (konferencijaRequest.getStatus() != null) {
            konferencijaEntity.setStatus(konferencijaRequest.getStatus());
        }
        if (konferencijaRequest.getNaziv() != null) {
            konferencijaEntity.setNaziv(konferencijaRequest.getNaziv());
        }
        if (konferencijaRequest.getUrl() != null) {
            konferencijaEntity.setUrl((konferencijaRequest.getUrl()));
        }

        konferencijaEntity.setId(id);

        konferencijaEntity = konferencijaRepository.saveAndFlush(konferencijaEntity);
        return findById(konferencijaEntity.getId());
    }

    @Override
    public void delete(Integer id) {
        konferencijaRepository.deleteById(id);

    }

    @Override
    public List<Ocjena> getAllOcjeneByKorisnikId(Integer id) {
        return konferencijaRepository.getAllOcjeneByKorisnikId(id).stream().map(l -> modelMapper.map(l, Ocjena.class)).collect(Collectors.toList());

    }


    @Override
    public Konferencija findById(Integer id) throws NotFoundException {
        return modelMapper.map(konferencijaRepository.findById(id).orElseThrow(NotFoundException::new), Konferencija.class);
    }

    @Override
    public List<KonferencijaEntity> findAllWhereKonferencijaIsNotFinished() {
        return konferencijaRepository.getAllNotFinishedKonferencije();
    }

    @Override
    public List<Konferencija> findAllKonferencijeByStatus(Boolean status) {
        return konferencijaRepository.findAllKonferencijeByStatus(status).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());

    }

    @Override
    public List<Konferencija> findAllKonferencijeByDatum(LocalDateTime datum) throws NotFoundException {
        return konferencijaRepository.findAllKonferencijeByDatum(datum).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());

    }

    @Override
    public List<Konferencija> findAllKonferencijeByNaziv(String nazivPattern) throws NotFoundException {
        return konferencijaRepository.findAllKonferencijeByNaziv(nazivPattern).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());
    }

    @Override
    public List<Konferencija> findAllKonferencijeByStatusAndDatum(Boolean status, LocalDateTime datum) throws NotFoundException {
        return konferencijaRepository.findAllKonferencijeByStatusAndDatum(status, datum).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());

    }

    @Override
    public List<Konferencija> findAllKonferencijeByStatusAndNaziv(Boolean status, String nazivPattern) throws NotFoundException {
        return konferencijaRepository.findAllKonferencijeByStatusAndNaziv(status, nazivPattern).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());

    }

    @Override
    public List<Konferencija> findAllKonferencijeByDatumAndNaziv(LocalDateTime datum, String nazivPattern) throws NotFoundException {
        return konferencijaRepository.findAllKonferencijeByDatumAndNaziv(datum, nazivPattern).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());

    }

    @Override
    public List<Konferencija> findAllKonferencijeByStatusAndDatumAndNaziv(Boolean status, LocalDateTime datum, String nazivPattern) throws NotFoundException {
        return konferencijaRepository.findAllKonferencijeByStatusAndDatumAndNaziv(status, datum, nazivPattern).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());

    }

    @Override
    public List<Konferencija> findAllKonferencijeByModerator(Integer idModeratora) throws NotFoundException {
        List<Konferencija> konferencijaEntities = konferencijaRepository.findAll().stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());
        List<Konferencija> noveKonferencije = new ArrayList<>();
        for (Konferencija k : konferencijaEntities) {
            List<Dogadjaj> dogadjaji = k.getDogadjajs().stream().filter(e -> e.getKorisnik().getId() == idModeratora).collect(Collectors.toList());
            if (dogadjaji.size() > 0) {
                k.setDogadjajs(dogadjaji);
                noveKonferencije.add(k);
            }
        }
        return noveKonferencije;
    }

    @Override
    public List<Konferencija> findAllKonferencijeByPosjetilac(Integer idPosjetioca) throws NotFoundException {
        List<PosjetilacEntity> posjetioci = posjetilacRepository.findByIdKorisnikId(idPosjetioca);

        List<Konferencija> konferencijaEntities = konferencijaRepository.findAll().stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());
        List<Konferencija> noveKonferencije = new ArrayList<>();
        List<Dogadjaj> noviDogadjaji=new ArrayList<>();

        for (Konferencija k : konferencijaEntities) {

            for (Dogadjaj d:k.getDogadjajs())
            {
                Posjetilac p=d.getPosjetilacs().stream().filter(e->e.getKorisnik().getId()==idPosjetioca).findFirst().orElse(null);
                if(p!=null)
                {
                    noviDogadjaji.add(d);
                }
            }
            if (noviDogadjaji.size() > 0) {
                k.setDogadjajs(noviDogadjaji);
                noveKonferencije.add(k);
            }
        }
        return noveKonferencije;




    }


    @Scheduled(cron = "0 * * * * *")
    public void checkStatusKonferencije() {
        //uzmi sve konferencije i proci kroz njih i provjeravam dal je zavrsena ako jeste finished jednako ture
        LocalDateTime now = LocalDateTime.now();
        List<KonferencijaEntity> nezavrseneKonferencije = findAllWhereKonferencijaIsNotFinished();
        for (KonferencijaEntity k : nezavrseneKonferencije) {
            LocalDateTime vrijeme = LocalDateTime.ofInstant(k.getEndTime().toInstant(), ZoneId.systemDefault());
            if (vrijeme.isAfter(now)) {
                k.setStatus(true);
                k = konferencijaRepository.saveAndFlush(k);
            }
        }


    }
}
