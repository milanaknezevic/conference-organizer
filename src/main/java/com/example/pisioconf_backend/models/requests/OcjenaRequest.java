package com.example.pisioconf_backend.models.requests;

import lombok.Data;

@Data
public class OcjenaRequest {
    private Integer zvjezdica;
    private String komentar;
    private Integer korisnikId;
    private Integer konferencijaId;
}
