package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.entities.OcjenaEntity;
import com.example.pisioconf_backend.models.entities.OcjenaEntityPK;
import com.example.pisioconf_backend.models.requests.OcjenaRequest;
import com.example.pisioconf_backend.services.OcjenaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ocjene")
public class OcjenaController {
    private final OcjenaService ocjenaService;

    public OcjenaController(OcjenaService ocjenaService) {
        this.ocjenaService = ocjenaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcjenaEntity insert(@RequestBody @Valid OcjenaRequest ocjenaRequest) throws NotFoundException {
        return ocjenaService.insert(ocjenaRequest);
    }


    @GetMapping("/{idKonferencije}")
    List<Ocjena> findAllOcjeneByKonferencijaId(@PathVariable Integer idKonferencije) {
        return ocjenaService.findAllOcjeneByKonferencijaId(idKonferencije);
    }

    @GetMapping
    List<Ocjena> findAll() {
        return ocjenaService.findAll();
    }
//mozda dodati pregled svih ocjena za neku konferenciju

    @PatchMapping("/{korisnikId}/{konferencijaId}")
    public Ocjena update(@PathVariable Integer korisnikId, @PathVariable Integer konferencijaId, @RequestBody OcjenaRequest ocjenaRequest) throws NotFoundException {
        OcjenaEntityPK id = new OcjenaEntityPK();
        id.setKorisnikId(korisnikId);
        id.setKonferencijaId(konferencijaId);
        return ocjenaService.update(id, ocjenaRequest);

    }

    @DeleteMapping("/{korisnikId}/{konferencijaId}")
    public void delete(@PathVariable Integer korisnikId,@PathVariable Integer konferencijaId) {
        OcjenaEntityPK id = new OcjenaEntityPK();
        id.setKorisnikId(korisnikId);
        id.setKonferencijaId(konferencijaId);
        ocjenaService.delete(id);
    }


}
