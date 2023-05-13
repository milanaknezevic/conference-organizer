package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Dogadjaj;
import com.example.pisioconf_backend.models.entities.*;
import com.example.pisioconf_backend.models.requests.DogadjajRequest;
import com.example.pisioconf_backend.repositories.*;
import com.example.pisioconf_backend.services.DogadjajService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DogadjajImplService implements DogadjajService {
    private final ModelMapper modelMapper;
    private final DogadjajRepository dogadjajRepository;
    private final RezervacijaRepository rezervacijaRepository;
    private final LokacijaRepository lokacijaRepository;
    private final SobaRepository sobaRepository;
    private final TipDogadjajaRepository tipDogadjajaRepository;
    private final SesijaRepository sesijaRepository;
    private final ResursRepository resursRepository;

    public DogadjajImplService(ModelMapper modelMapper, DogadjajRepository dogadjajRepository, RezervacijaRepository rezervacijaRepository,
                               LokacijaRepository lokacijaRepository, SobaRepository sobaRepository, TipDogadjajaRepository tipDogadjajaRepository,
                               SesijaRepository sesijaRepository,
                               ResursRepository resursRepository) {
        this.modelMapper = modelMapper;
        this.dogadjajRepository = dogadjajRepository;
        this.rezervacijaRepository = rezervacijaRepository;
        this.lokacijaRepository = lokacijaRepository;
        this.sobaRepository = sobaRepository;
        this.tipDogadjajaRepository = tipDogadjajaRepository;
        this.sesijaRepository = sesijaRepository;
        this.resursRepository = resursRepository;
    }

    @Override
    public List<Dogadjaj> findAll() {
        return dogadjajRepository.findAll().stream().map(l -> modelMapper.map(l, Dogadjaj.class)).collect(Collectors.toList());
    }

    @Override
    public Dogadjaj findById(Integer id) throws NotFoundException {
        return modelMapper.map(dogadjajRepository.findById(id).orElseThrow(NotFoundException::new), Dogadjaj.class);
    }

    @Override
    public void delete(Integer id) {
        dogadjajRepository.deleteById(id);
    }

    @Override
    public Dogadjaj insert(DogadjajRequest dogadjajRequest) throws NotFoundException {
        LokacijaEntity lokacijaEntity = lokacijaRepository.findById(dogadjajRequest.getLokacijaId()).get();
        TipDogadjajaEntity tipDogadjajaEntity = tipDogadjajaRepository.findById(dogadjajRequest.getTipDogadjaja()).get();
        SesijaEntity sesijaEntity = sesijaRepository.findById(dogadjajRequest.getSesijaId()).get();

        DogadjajEntity dogadjajEntity = modelMapper.map(dogadjajRequest, DogadjajEntity.class);
        dogadjajEntity.setSesijaBySesijaId(sesijaEntity);
        dogadjajEntity.setTipDogadjajaByTipDogadjajaId(tipDogadjajaEntity);
        dogadjajEntity.setLokacijaByLokacijaId(lokacijaEntity);

        dogadjajEntity.setId(null);
        dogadjajEntity = dogadjajRepository.saveAndFlush(dogadjajEntity);
        return findById(dogadjajEntity.getId());
    }

    @Override
    public Dogadjaj update(Integer id, DogadjajRequest dogadjajRequest) throws NotFoundException {
        DogadjajEntity dogadjajEntity = dogadjajRepository.findById(id).get(); // modelMapper.map(konferencijaRequest, KonferencijaEntity.class);
        if (dogadjajRequest.getStartTime() != null) {
            dogadjajEntity.setStartTime(dogadjajRequest.getStartTime());
        }
        if (dogadjajRequest.getEndTime() != null) {
            dogadjajEntity.setEndTime(dogadjajRequest.getEndTime());
        }
        if (dogadjajRequest.getNaziv() != null) {
            dogadjajEntity.setNaziv(dogadjajRequest.getNaziv());
        }
        if (dogadjajRequest.getUrl() != null) {
            dogadjajEntity.setUrl(dogadjajRequest.getUrl());
        }

        dogadjajEntity.setId(id);

        dogadjajEntity = dogadjajRepository.saveAndFlush(dogadjajEntity);
        return findById(dogadjajEntity.getId());
    }

    //@Scheduled(cron = "0 */30 * ? * *")
    @Scheduled(cron = "* * * * * *")
    public void checkDogadjajFinished() {
        LocalDateTime now = LocalDateTime.now();
        List<DogadjajEntity> dogadjaji = dogadjajRepository.findAll();
        for (DogadjajEntity d : dogadjaji) {
            LocalDateTime vrijeme = LocalDateTime.ofInstant(d.getEndTime().toInstant(), ZoneId.systemDefault());
            if (vrijeme.isAfter(now)) {
                SobaEntity soba = d.getSobaBySobaId();
                soba.setStatus(false);
                List<RezervacijaEntity> rezervacije = rezervacijaRepository.getAllRezervacijeByDogadjajID(d.getId());
                for (RezervacijaEntity r : rezervacije) {
                    //update resurs
                    int kolicina = r.getKolicina();
                    ResursEntity resurs = r.getResursByResursId();
                    resurs.setKolicina(resurs.getKolicina() + kolicina);
                    resurs = resursRepository.saveAndFlush(resurs);


                    //obrisi rezeraviju
                    rezervacijaRepository.delete(r);

                }

                soba = sobaRepository.saveAndFlush(soba);
            }
        }
    }

    @Scheduled(cron = "0 */30 * ? * *")
    public void checkLokacije() {
        LocalDateTime now = LocalDateTime.now();
        List<LokacijaEntity> lokacije = lokacijaRepository.findAll();
        for (LokacijaEntity l : lokacije) {
            List<SobaEntity> sobe = l.getSobe();
            SobaEntity s = sobe.stream().filter(e -> !e.getStatus()).findFirst().orElse(null);
            if (s == null) {
                l.setStatus(true);
            } else {
                l.setStatus(false);
            }
            l = lokacijaRepository.saveAndFlush(l);
        }
    }
}