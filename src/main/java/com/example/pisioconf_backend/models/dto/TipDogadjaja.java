package com.example.pisioconf_backend.models.dto;

import com.example.pisioconf_backend.models.entities.DogadjajEntity;
import lombok.Data;

import java.util.List;

@Data
public class TipDogadjaja {
    private Integer id;
    private String naziv;
    private List<Dogadjaj> dogadjaji;
}
