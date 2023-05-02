package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Lokacija;
import com.example.pisioconf_backend.services.KonferencijaService;
import com.example.pisioconf_backend.services.LokacijaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lokacije")
public class LokacijaController {
    private final LokacijaService lokacijaService;
    private final KonferencijaService konferencijaService;

    public LokacijaController(LokacijaService lokacijaService, KonferencijaService konferencijaService) {
        this.lokacijaService = lokacijaService;
        this.konferencijaService = konferencijaService;
    }

    @GetMapping
    List<Lokacija> findAll() {
        return lokacijaService.findAll();
    }

    @GetMapping("{id}/konferencije")
    List<Konferencija> getAllKonferencijeByLocationId(@PathVariable Integer id) {
        return konferencijaService.getAllKonferencijeByLocationId(id);
    }

}
