package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "ocjena")
public class OcjenaEntity {
    @EmbeddedId
    private OcjenaEntityPK id;
    @Basic
    @Column(name = "zvjezdica")
    private Integer zvjezdica;
    @Basic
    @Column(name = "komentar")
    private String komentar;
    @MapsId("KORISNIK_id")
    @ManyToOne
    @JoinColumn(name = "KORISNIK_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnikByKorisnikId;
    @MapsId("KONFERENCIJA_id")
    @ManyToOne
    @JoinColumn(name = "KONFERENCIJA_id", referencedColumnName = "id", nullable = false)
    private KonferencijaEntity konferencijaByKonferencijaId;

}
