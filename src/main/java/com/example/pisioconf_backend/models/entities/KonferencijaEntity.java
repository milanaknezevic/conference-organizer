package com.example.pisioconf_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "konferencija")
public class KonferencijaEntity {
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
    @Column(name = "status")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "organizator_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @ManyToOne
    @JoinColumn(name = "LOKACIJA_id", referencedColumnName = "id")
    private LokacijaEntity lokacija;
    @OneToMany(mappedBy = "konferencija")
    private List<OcjenaEntity> ocjenas;
    @OneToMany(mappedBy = "konferencija")
    private List<SesijaEntity> sesijas;

}
