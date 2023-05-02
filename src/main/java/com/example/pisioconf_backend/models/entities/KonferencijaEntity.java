package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    @JoinColumn(name = "moderator_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnikByModeratorId;
    @ManyToOne
    @JoinColumn(name = "organizator_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnikByOrganizatorId;
    @ManyToOne
    @JoinColumn(name = "LOKACIJA_id", referencedColumnName = "id")
    private LokacijaEntity lokacijaByLokacijaId;
    @OneToMany(mappedBy = "konferencijaByKonferencijaId")
    private List<OcjenaEntity> ocjene;
    @OneToMany(mappedBy = "konferencijaByKonferencijaId", cascade = CascadeType.ALL)
    private List<SesijaEntity> sesije;

}
