package com.example.pisioconf_backend.models.dto;

import lombok.Data;

@Data
public class Soba {
    private Integer id;
    private String naziv;
    private String kapacitet;
    private Boolean status;
}
