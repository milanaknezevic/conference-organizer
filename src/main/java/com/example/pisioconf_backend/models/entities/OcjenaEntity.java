package com.example.pisioconf_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
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
    @Basic
    @Column(name = "datum")
    private Date datum;
    @MapsId("korisnikId")
    @ManyToOne
    @JoinColumn(name = "KORISNIK_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @MapsId("konferencijaId")
    @ManyToOne
    @JoinColumn(name = "KONFERENCIJA_id", referencedColumnName = "id", nullable = false)
    private KonferencijaEntity konferencija;

}
