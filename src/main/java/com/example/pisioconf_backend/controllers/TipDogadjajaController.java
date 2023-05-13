package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.models.dto.TipDogadjaja;
import com.example.pisioconf_backend.services.TipDogadjajaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipovi_dogadjaja")
public class TipDogadjajaController {
    private final TipDogadjajaService tipDogadjajaService;

    public TipDogadjajaController(TipDogadjajaService tipDogadjajaService) {
        this.tipDogadjajaService = tipDogadjajaService;
    }

    @GetMapping
    List<TipDogadjaja> findAll() {
        return tipDogadjajaService.findAll();
    }
}
