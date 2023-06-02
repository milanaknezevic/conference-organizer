package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.models.dto.Resurs;
import com.example.pisioconf_backend.models.dto.TipDogadjaja;
import com.example.pisioconf_backend.services.ResursService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resursi")
public class ResursController {

    public final ResursService resursService;

    public ResursController(ResursService resursService) {
        this.resursService = resursService;
    }

    @GetMapping
    List<Resurs> findAll() {
        return resursService.findAll();
    }

    @GetMapping("/{idLokacije}")
    List<Resurs> findAllByLokacija(@PathVariable Integer idLokacije) {
        return resursService.findALlByLokacija(idLokacije);
    }
}
