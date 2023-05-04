package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.dto.Posjetilac;
import com.example.pisioconf_backend.models.entities.OcjenaEntity;
import com.example.pisioconf_backend.models.entities.PosjetilacEntity;
import com.example.pisioconf_backend.models.entities.PosjetilacEntityPK;
import com.example.pisioconf_backend.models.requests.OcjenaRequest;
import com.example.pisioconf_backend.models.requests.PosjetilacRequest;
import com.example.pisioconf_backend.services.PosjetilacService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posjetioci")
public class PosjetilacController {
    private final PosjetilacService posjetilacService;

    public PosjetilacController(PosjetilacService posjetilacService) {
        this.posjetilacService = posjetilacService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PosjetilacEntity insert(@RequestBody @Valid PosjetilacRequest posjetilacRequest) throws NotFoundException {
        return posjetilacService.insert(posjetilacRequest);
    }
    @GetMapping
    List<Posjetilac> findAll() {
        return posjetilacService.findAll();
    }

    @DeleteMapping("/{korisnikId}/{dogadjajId}")
    public void delete(@PathVariable Integer korisnikId,@PathVariable Integer dogadjajId) {
        PosjetilacEntityPK id=new PosjetilacEntityPK();
        id.setDogadjajId(dogadjajId);
        id.setKorisnikId(korisnikId);
        posjetilacService.delete(id);
    }


}
