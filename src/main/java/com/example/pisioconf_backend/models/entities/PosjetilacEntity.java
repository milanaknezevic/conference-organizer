package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "posjetilac")
public class PosjetilacEntity {
    @EmbeddedId
    private PosjetilacEntityPK id;

    @MapsId("KORISNIK_id")
    @ManyToOne
    @JoinColumn(name = "KORISNIK_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnikByKorisnikId;
    @MapsId("DOGADJAJ_id")
    @ManyToOne
    @JoinColumn(name = "DOGADJAJ_id", referencedColumnName = "id", nullable = false)
    private DogadjajEntity dogadjajByDogadjajId;

}
