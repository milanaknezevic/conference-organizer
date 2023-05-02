package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "rezervacija")
public class RezervacijaEntity {
    @EmbeddedId
    private RezervacijaEntityPK id;
    @Basic
    @Column(name = "kolicina")
    private Integer kolicina;
    @MapsId("RESURS_id")
    @ManyToOne
    @JoinColumn(name = "RESURS_id", referencedColumnName = "id", nullable = false)
    private ResursEntity resursByResursId;
    @MapsId("DOGADJAJ_id")
    @ManyToOne
    @JoinColumn(name = "DOGADJAJ_id", referencedColumnName = "id", nullable = false)
    private DogadjajEntity dogadjajByDogadjajId;

}
