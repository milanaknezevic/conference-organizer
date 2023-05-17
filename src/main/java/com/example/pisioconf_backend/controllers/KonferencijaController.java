package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Korisnik;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;
import com.example.pisioconf_backend.services.KonferencijaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/konferencije")
public class KonferencijaController {

    private final KonferencijaService konferencijaService;

    public KonferencijaController(KonferencijaService konferencijaService) {
        this.konferencijaService = konferencijaService;
    }

    @GetMapping("/datum")
    public List<Konferencija> findAllKonferencijeByDatum(@RequestParam("datum") LocalDateTime datum)
    {
        return konferencijaService.findAllKonferencijeByDatum(datum);
    }
    @GetMapping("/naziv")
    public List<Konferencija> findAllKonferecijeByNaziv(@RequestParam("naziv") String naziv)
    {
        return konferencijaService.findAllKonferencijeByNaziv(naziv);
    }
    @GetMapping("/status")
    public List<Konferencija> findKonferencijeByStatus(@RequestParam("status") Boolean status)
    {
        return konferencijaService.findAllKonferencijeByStatus(status);
    }
    @GetMapping("/status-datum")
    public List<Konferencija> findAllKonferencijeByStatusAndDatum(@RequestParam("status") Boolean status,@RequestParam("datum") LocalDateTime datum)
    {
        return konferencijaService.findAllKonferencijeByStatusAndDatum(status,datum);
    }
    @GetMapping("/status-naziv")
    public List<Konferencija> findAllKonferencijeByStatusAndNaziv(@RequestParam("status") Boolean status, @RequestParam("naziv") String naziv)
    {
        return konferencijaService.findAllKonferencijeByStatusAndNaziv(status,naziv);
    }

    @GetMapping("/datum-naziv")
    public List<Konferencija> findAllKonferencijeByDatumAndNaziv(@RequestParam("datum") LocalDateTime datum, @RequestParam("naziv") String naziv)
    {
        return konferencijaService.findAllKonferencijeByDatumAndNaziv(datum,naziv);
    }

    @GetMapping("/status-datum-naziv")
    public List<Konferencija> findAllKonferencijeByStatusAndDatumAndNaziv(@RequestParam("status") Boolean status,@RequestParam("datum") LocalDateTime datum, @RequestParam("naziv") String naziv)
    {
        return konferencijaService.findAllKonferencijeByStatusAndDatumAndNaziv(status,datum,naziv);
    }



    @GetMapping("/all")
    List<Konferencija> findAll() {
        return konferencijaService.findAll();
    }
    @GetMapping("/{idModeratora}/moderator-conf")
    List<Konferencija> findAllByModeratorId(@PathVariable Integer idModeratora) {
        return konferencijaService.findAllByModeratorId(idModeratora);
    }



    @GetMapping("/{id}")
    public Konferencija findById(@PathVariable Integer id) throws NotFoundException {
        return konferencijaService.findById(id);
    }




    @GetMapping("/nezavrsene")
    List<KonferencijaEntity> findAllWhereKonferencijaIsNotFInished()
    {
        return  konferencijaService.findAllWhereKonferencijaIsNotFinished();
    }
    @GetMapping("/{idKonferencije}/ocjene")
    public List<Ocjena> findAllOcjeneForKonferencija(@PathVariable Integer idKonferencije) throws NotFoundException{
        return konferencijaService.getAllOcjeneByKorisnikId(idKonferencije);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        konferencijaService.delete(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Konferencija insert(@RequestBody @Valid KonferencijaRequest konferencijaRequest) throws NotFoundException {
        return konferencijaService.insert(konferencijaRequest);
    }



    @PatchMapping("/{id}")
    public Konferencija update(@PathVariable Integer id, @RequestBody KonferencijaRequest konferencijaRequest) throws NotFoundException {
        return konferencijaService.update(id, konferencijaRequest);
    }


}
