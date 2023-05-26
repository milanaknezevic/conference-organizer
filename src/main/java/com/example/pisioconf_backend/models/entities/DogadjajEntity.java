package com.example.pisioconf_backend.models.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "dogadjaj")
public class DogadjajEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "start_time")
    private Date startTime;
    @Basic
    @Column(name = "end_time")
    private Date endTime;
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @Basic
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "KONFERENCIJA_id", referencedColumnName = "id", nullable = false)
    private KonferencijaEntity konferencija;
    @ManyToOne
    @JoinColumn(name = "TIP_DOGADJAJA_id", referencedColumnName = "id", nullable = false)
    private TipDogadjajaEntity tipDogadjaja;
    @ManyToOne
    @JoinColumn(name = "LOKACIJA_id", referencedColumnName = "id", nullable = false)
    private LokacijaEntity lokacija;
    @ManyToOne
    @JoinColumn(name = "soba_id", referencedColumnName = "id", nullable = false)
    private SobaEntity soba;
    @ManyToOne
    @JoinColumn(name = "moderator_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @OneToMany(mappedBy = "dogadjaj")
    @Cascade(CascadeType.DELETE)
    private List<PosjetilacEntity> posjetilacs;
    @OneToMany(mappedBy = "dogadjaj")
    @Cascade(CascadeType.DELETE)
    private List<RezervacijaEntity> rezervacijas;

}
