package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.dto.Rezervacija;
import com.example.pisioconf_backend.models.entities.OcjenaEntity;
import com.example.pisioconf_backend.models.entities.OcjenaEntityPK;
import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;
import com.example.pisioconf_backend.models.requests.OcjenaRequest;
import com.example.pisioconf_backend.models.requests.RezervacijaRequest;
import com.example.pisioconf_backend.services.OcjenaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ocjene")
public class OcjenaController {
    private final OcjenaService ocjenaService;

    public OcjenaController(OcjenaService ocjenaService) {
        this.ocjenaService = ocjenaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcjenaEntity insert(@RequestBody @Valid OcjenaRequest ocjenaRequest) throws NotFoundException {
        return ocjenaService.insert(ocjenaRequest);
    }
    @GetMapping
    List<Ocjena> findAll() {
        return ocjenaService.findAll();
    }



    @PutMapping("/{resursId}/{dogadjajId}")
    public Ocjena update( @PathVariable("resursId") Integer resursId, @PathVariable("dogadjajId") Integer dogadjajId, @RequestBody OcjenaRequest konferencijaRequest) throws NotFoundException
    {
//        OcjenaEntityPK id = new RezervacijaEntityPK(resursId, dogadjajId);
//        RezervacijaEntity a = rezervacijaService.azurirajRezervaciju(id, novaKolicina);
//        if (a != null) {
//            return new ResponseEntity<>(a, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return null;
    }
}
