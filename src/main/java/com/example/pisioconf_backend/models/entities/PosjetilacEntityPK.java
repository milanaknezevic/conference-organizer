package com.example.pisioconf_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class PosjetilacEntityPK implements Serializable {
    @Column(name = "KORISNIK_id")
   private Integer korisnikId;
    @Column(name = "DOGADJAJ_id")
   private Integer dogadjajId;

}
