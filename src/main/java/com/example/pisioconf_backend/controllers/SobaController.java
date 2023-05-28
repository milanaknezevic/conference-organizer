package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.models.dto.Soba;
import com.example.pisioconf_backend.services.SobaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sobe")
public class SobaController {
    private final SobaService sobaService;

    public SobaController(SobaService sobaService) {
        this.sobaService = sobaService;
    }

    @GetMapping("/{idLokacije}")
    List<Soba>  findAllByLokacijaId(@PathVariable Integer idLokacije)
    {
        return sobaService.findAllByLokacijaId(idLokacije);

    }

}
