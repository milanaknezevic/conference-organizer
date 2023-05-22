package com.example.pisioconf_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "posjetilac")
public class PosjetilacEntity {
    @EmbeddedId
    private PosjetilacEntityPK id;
    @MapsId("korisnikId")
    @ManyToOne
    @JoinColumn(name = "KORISNIK_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;

    @MapsId("dogadjajId")
    @ManyToOne
    @JoinColumn(name = "DOGADJAJ_id", referencedColumnName = "id", nullable = false)
    private DogadjajEntity dogadjaj;

}
