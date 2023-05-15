package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.entities.OcjenaEntity;
import com.example.pisioconf_backend.models.entities.OcjenaEntityPK;
import com.example.pisioconf_backend.models.requests.OcjenaRequest;
import com.example.pisioconf_backend.repositories.OcjenaRepository;
import com.example.pisioconf_backend.services.OcjenaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
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

    @Override
    public Ocjena update(OcjenaEntityPK id, OcjenaRequest ocjenaRequest) throws NotFoundException {
        OcjenaEntity ocjenaEntity = ocjenaRepository.findById(id).get();
        if (ocjenaRequest.getKomentar() != null) {
            ocjenaEntity.setKomentar(ocjenaRequest.getKomentar());
        }
        if (ocjenaRequest.getZvjezdica() != null) {
            ocjenaEntity.setZvjezdica(ocjenaRequest.getZvjezdica());
        }
        ocjenaEntity.setId(id);
        ocjenaEntity = ocjenaRepository.saveAndFlush(ocjenaEntity);
        return null; // findById(ocjenaEntity.getId());
    }

    @Override
    public void delete(OcjenaEntityPK id) {
        ocjenaRepository.deleteById(id);
    }

    @Override
    public List<Ocjena> findAllOcjeneByKonferencijaId(Integer idKonferencije) {
        return ocjenaRepository.findAllByKonferencijaByKonferencijaId(idKonferencije).stream().map(l -> modelMapper.map(l, Ocjena.class)).collect(Collectors.toList());
    }
}
