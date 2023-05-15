package com.example.pisioconf_backend.models.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PosjetilacEntityPK implements Serializable {
    @Column(name = "KORISNIK_id")
    private Integer korisnikId;
    @Column(name = "DOGADJAJ_id")
    private Integer dogadjajId;

}
