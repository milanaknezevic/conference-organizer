package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Dogadjaj;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.requests.DogadjajRequest;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;
import com.example.pisioconf_backend.services.DogadjajService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dogadjaji")
public class DogadjajController // extends CrudController<Integer, AssetTypeRequest, AssetType> {
{
    private final DogadjajService dogadjajService;

    public DogadjajController(DogadjajService dogadjajService) {
        this.dogadjajService = dogadjajService;
    }


    @GetMapping
    List<Dogadjaj> findAll() {
        return dogadjajService.findAll();
    }

    @GetMapping("/{id}")
    public Dogadjaj findById(@PathVariable Integer id) throws NotFoundException {
        return dogadjajService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        dogadjajService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dogadjaj insert(@RequestBody @Valid DogadjajRequest dogadjajRequest) throws NotFoundException {
        return dogadjajService.insert(dogadjajRequest);
    }


    @PatchMapping("/{id}")
    public Dogadjaj update(@PathVariable Integer id, @RequestBody DogadjajRequest dogadjajRequest) throws NotFoundException {
        return dogadjajService.update(id, dogadjajRequest);
    }
}
