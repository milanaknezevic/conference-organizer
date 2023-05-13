package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Sesija;
import com.example.pisioconf_backend.models.requests.SesijaRequest;
import com.example.pisioconf_backend.services.SesijaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @GetMapping("/{id}")
    public Sesija findById(@PathVariable Integer id) throws NotFoundException {
        return sesijaService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        sesijaService.delete(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sesija insert(@RequestBody @Valid SesijaRequest sesijaRequest) throws NotFoundException {
        return sesijaService.insert(sesijaRequest);
    }

    @PatchMapping("/{id}")
    public Sesija update(@PathVariable Integer id, @RequestBody SesijaRequest sesijaRequest) throws NotFoundException {
        return sesijaService.update(id, sesijaRequest);
    }

}
