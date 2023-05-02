package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;
import com.example.pisioconf_backend.models.requests.RezervacijaRequest;
import com.example.pisioconf_backend.services.RezervacijaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
