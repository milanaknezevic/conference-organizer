package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;
import com.example.pisioconf_backend.services.KonferencijaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/konferencije")
public class KonferencijaController {

    private final KonferencijaService konferencijaService;

    public KonferencijaController(KonferencijaService konferencijaService) {
        this.konferencijaService = konferencijaService;
    }

    @GetMapping
    List<Konferencija> findAll() {
        return konferencijaService.findAll();
    }

    @GetMapping("/{id}")
    public Konferencija findById(@PathVariable Integer id) throws NotFoundException {
        return konferencijaService.findById(id);
    }
    @GetMapping("/{idKonferencije}/ocjene")
    public List<Ocjena> findAllOcjeneForKonferencija(@PathVariable Integer idKonferencije) throws NotFoundException{
        return konferencijaService.getAllOcjeneByKorisnikId(idKonferencije);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        konferencijaService.delete(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Konferencija insert(@RequestBody @Valid KonferencijaRequest konferencijaRequest) throws NotFoundException {
        return konferencijaService.insert(konferencijaRequest);
    }

    @PatchMapping("/{id}")
    public Konferencija update(@PathVariable Integer id, @RequestBody KonferencijaRequest konferencijaRequest) throws NotFoundException {
        return konferencijaService.update(id, konferencijaRequest);
    }


}
