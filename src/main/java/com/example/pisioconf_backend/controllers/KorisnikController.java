package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.services.KorisnikService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/korisnici")
public class KorisnikController {


    public final KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping("/{id}/moderator_konferencije")
    public List<Konferencija> getKonferencijeZaModeratora(@PathVariable Integer id) {
        return korisnikService.getKonferencijeZaModeratora(id);
    }
    @GetMapping("/{id}organizator_konferencije")
    public List<Konferencija> getKonferencijeZaOrganizatora(@PathVariable Integer id)
    {
        return korisnikService.getKonferencijeZaOrganizatora(id);
    }
}
