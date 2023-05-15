package com.example.pisioconf_backend.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class Lokacija {
    private Integer id;
    private String adresa;
    private Boolean status;
    private List<Resurs> resursi;
    private List<Soba> sobe;
}
