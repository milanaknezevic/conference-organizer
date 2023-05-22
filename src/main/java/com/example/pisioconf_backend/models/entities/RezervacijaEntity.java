package com.example.pisioconf_backend.models.entities;

import lombok.*;

import javax.persistence.*;
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

    @MapsId("resursId")
    @ManyToOne
    @JoinColumn(name = "RESURS_id", referencedColumnName = "id", nullable = false)
    private ResursEntity resurs;

    @MapsId("dogadjajId")
    @ManyToOne
    @JoinColumn(name = "DOGADJAJ_id", referencedColumnName = "id", nullable = false)
    private DogadjajEntity dogadjaj;

}
