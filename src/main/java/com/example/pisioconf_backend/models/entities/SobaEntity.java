package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "soba")
public class SobaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @Basic
    @Column(name = "kapacitet")
    private String kapacitet;
    @Basic
    @Column(name = "status")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "LOKACIJA_id", referencedColumnName = "id", nullable = false)
    private LokacijaEntity lokacijaByLokacijaId;

}
