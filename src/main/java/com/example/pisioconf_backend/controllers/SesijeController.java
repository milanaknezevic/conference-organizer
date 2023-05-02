package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.models.dto.Sesija;
import com.example.pisioconf_backend.models.dto.TipDogadjaja;
import com.example.pisioconf_backend.services.SesijaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sesije")
public class SesijeController {

    private final SesijaService sesijaService;

    public SesijeController(SesijaService sesijaService) {
        this.sesijaService = sesijaService;
    }

    @GetMapping
    List<Sesija> findAll() {
        return sesijaService.findAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        sesijaService.delete(id);
    }

}
