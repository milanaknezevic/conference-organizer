package com.example.pisioconf_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "lokacija")
public class LokacijaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "adresa")
    private String adresa;
    @OneToMany(mappedBy = "lokacija")
    private List<DogadjajEntity> dogadjajs;
    @OneToMany(mappedBy = "lokacija")
    private List<KonferencijaEntity> konferencijas;
    @OneToMany(mappedBy = "lokacija")
    private List<ResursEntity> resurs;
    @OneToMany(mappedBy = "lokacija")
    private List<SobaEntity> sobas;

}
