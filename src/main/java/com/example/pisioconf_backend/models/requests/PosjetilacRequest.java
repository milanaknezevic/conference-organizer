package com.example.pisioconf_backend.models.requests;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PosjetilacRequest {
    private Integer korisnikId;
    private Integer dogadjajId;
}
