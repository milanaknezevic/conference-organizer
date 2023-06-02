package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Rezervacija;
import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.entities.RezervacijaEntityPK;
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

    @GetMapping("/{resursId}/{dogadjajId}")
    public Rezervacija findById(@PathVariable Integer resursId, @PathVariable Integer dogadjajId) throws NotFoundException {
        RezervacijaEntityPK id = new RezervacijaEntityPK();
        id.setResursId(resursId);
        id.setDogadjajId(dogadjajId);
        return rezervacijaService.findById(id);
    }

    @GetMapping("/{dogadjajId}")
    public List<RezervacijaEntity> findByDogadjajId(@PathVariable Integer dogadjajId) throws NotFoundException {
        return rezervacijaService.findAllByDogadjajId(dogadjajId);
    }

    @PatchMapping("/{resursId}/{dogadjajId}")
    public Rezervacija update(@PathVariable Integer resursId, @PathVariable Integer dogadjajId, @RequestBody RezervacijaRequest rezervacijaRequest) throws NotFoundException {


        RezervacijaEntityPK id = new RezervacijaEntityPK();
        id.setResursId(resursId);
        id.setDogadjajId(dogadjajId);
        return rezervacijaService.update(id, rezervacijaRequest);

    }


    @PatchMapping("/rezervacija_update")
    public void updateRez(@RequestBody List<RezervacijaRequest> rezervacijaRequestList) throws NotFoundException {
        for (RezervacijaRequest r : rezervacijaRequestList) {
            RezervacijaEntityPK id = new RezervacijaEntityPK();
            id.setResursId(r.getResursId());
            id.setDogadjajId(r.getDogadjajId());
            rezervacijaService.updateRezervacija(id, r.getKolicina());
        }
    }
    @PatchMapping("/update")
    public void updateRezervacije(@RequestBody RezervacijaRequest rezervacijaRequest) throws NotFoundException {
       RezervacijaEntityPK id = new RezervacijaEntityPK();
            id.setResursId(rezervacijaRequest.getResursId());
            id.setDogadjajId(rezervacijaRequest.getDogadjajId());
            rezervacijaService.updateRezervacija(id, rezervacijaRequest.getKolicina());

    }



    @DeleteMapping("/{resursId}/{dogadjajId}")
    public void delete(@PathVariable Integer resursId, @PathVariable Integer dogadjajId) {
        RezervacijaEntityPK id = new RezervacijaEntityPK();
        id.setResursId(resursId);
        id.setDogadjajId(dogadjajId);
        rezervacijaService.delete(id);
    }

}
