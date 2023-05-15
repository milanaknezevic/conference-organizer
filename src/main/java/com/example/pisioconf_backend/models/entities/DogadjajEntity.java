package com.example.pisioconf_backend.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @JoinColumn(name = "SESIJA_id", referencedColumnName = "id", nullable = false)
    private SesijaEntity sesijaBySesijaId;
    @ManyToOne
    @JoinColumn(name = "TIP_DOGADJAJA_id", referencedColumnName = "id", nullable = false)
    private TipDogadjajaEntity tipDogadjajaByTipDogadjajaId;
    @ManyToOne
    @JoinColumn(name = "LOKACIJA_id", referencedColumnName = "id", nullable = false)
    private LokacijaEntity lokacijaByLokacijaId;
    @OneToMany(mappedBy = "dogadjajByDogadjajId")
    private List<PosjetilacEntity> posjetioci;
    @OneToMany(mappedBy = "dogadjajByDogadjajId")
    private List<RezervacijaEntity> rezervacije;
    @OneToOne
    @JoinColumn(name = "SOBA_id", referencedColumnName = "id", nullable = false)
    private SobaEntity sobaBySobaId;

}
