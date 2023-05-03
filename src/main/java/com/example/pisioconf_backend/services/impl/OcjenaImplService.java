package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.controllers.repositories.OcjenaRepository;
import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.dto.Rezervacija;
import com.example.pisioconf_backend.models.entities.*;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;
import com.example.pisioconf_backend.models.requests.OcjenaRequest;
import com.example.pisioconf_backend.services.OcjenaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OcjenaImplService implements OcjenaService {
    private final ModelMapper modelMapper;
    private final OcjenaRepository ocjenaRepository;

    public OcjenaImplService(ModelMapper modelMapper, OcjenaRepository ocjenaRepository) {
        this.modelMapper = modelMapper;
        this.ocjenaRepository = ocjenaRepository;
    }

    @Override
    public OcjenaEntity insert(OcjenaRequest ocjenaRequest) throws NotFoundException {
        OcjenaEntityPK ocjenaEntityPK = new OcjenaEntityPK();
        ocjenaEntityPK.setKonferencijaId(ocjenaRequest.getKonferencijaId());
        ocjenaEntityPK.setKorisnikId(ocjenaRequest.getKorisnikId());
        OcjenaEntity ocjenaEntity = modelMapper.map(ocjenaRequest, OcjenaEntity.class);
        ocjenaEntity.setId(ocjenaEntityPK);
        ocjenaEntity = ocjenaRepository.saveAndFlush(ocjenaEntity);
//        ResursEntity resursEntity = resursRepository.findById(rezervacijaRequest.getResursId()).get();
//        resursEntity.setKolicina(resursEntity.getKolicina()-rezervacijaRequest.getKolicina());
//        resursEntity= resursRepository.saveAndFlush(resursEntity);
        return null;
    }

    @Override
    public List<Ocjena> findAll() {
        return ocjenaRepository.findAll().stream().map(l -> modelMapper.map(l, Ocjena.class)).collect(Collectors.toList());

    }


//    public Ocjena update(Integer id, OcjenaRequest ocjenaRequest) throws NotFoundException {
//        KonferencijaEntity konferencijaEntity = konferencijaRepository.findById(id).get(); // modelMapper.map(konferencijaRequest, KonferencijaEntity.class);
//        if (konferencijaRequest.getStartTime() != null) {
//            konferencijaEntity.setStartTime(konferencijaRequest.getStartTime());
//        }
//        if (konferencijaRequest.getEndTime() != null) {
//            konferencijaEntity.setEndTime(konferencijaRequest.getEndTime());
//        }
//        if (konferencijaRequest.getStatus() != null) {
//            konferencijaEntity.setStatus(konferencijaRequest.getStatus());
//        }
//        if (konferencijaRequest.getNaziv() != null) {
//            konferencijaEntity.setNaziv(konferencijaRequest.getNaziv());
//        }
//
//        konferencijaEntity.setId(id);
//
//        konferencijaEntity = konferencijaRepository.saveAndFlush(konferencijaEntity);
//        return findById(konferencijaEntity.getId());
//    }
}
