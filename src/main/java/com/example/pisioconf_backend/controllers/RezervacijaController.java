package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Rezervacija;
import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.entities.RezervacijaEntityPK;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;
import com.example.pisioconf_backend.models.requests.RezervacijaRequest;
import com.example.pisioconf_backend.services.RezervacijaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {
    private final RezervacijaService rezervacijaService;

    public RezervacijaController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RezervacijaEntity insert(@RequestBody @Valid RezervacijaRequest rezervacijaRequest) throws NotFoundException {
        return rezervacijaService.insert(rezervacijaRequest);
    }
    @GetMapping
    List<Rezervacija> findAll() {
        return rezervacijaService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        rezervacijaService.delete(id);
    }
//    @PatchMapping("/{resursId}/{dogadjajId}")
//    public Rezervacija update(@PathVariable Integer resursId,@PathVariable Integer dogadjajId, @RequestBody RezervacijaRequest rezervacijaRequest) throws NotFoundException {
//        RezervacijaEntityPK id=new RezervacijaEntityPK(resursId,dogadjajId);
//        return rezervacijaService.update(id, rezervacijaRequest);
//    }

}
