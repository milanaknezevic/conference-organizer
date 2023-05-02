package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "resurs")
public class ResursEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @Basic
    @Column(name = "kolicina")
    private Integer kolicina;
    @Basic
    @Column(name = "status")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "LOKACIJA_id", referencedColumnName = "id", nullable = false)
    private LokacijaEntity lokacijaByLokacijaId;
    @OneToMany(mappedBy = "resursByResursId")
    private List<RezervacijaEntity> rezervacije;

}
