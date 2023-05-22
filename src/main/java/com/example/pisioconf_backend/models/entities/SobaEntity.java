package com.example.pisioconf_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    @OneToMany(mappedBy = "soba")
    private List<DogadjajEntity> dogadjajs;
    @ManyToOne
    @JoinColumn(name = "LOKACIJA_id", referencedColumnName = "id", nullable = false)
    private LokacijaEntity lokacija;

}
