package com.example.pisioconf_backend.models.requests;

import lombok.Data;

@Data
public class RezervacijaRequest {
    private Integer kolicina;
    private Integer dogadjajId;
    private Integer resursId;
}
