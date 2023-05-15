package com.example.pisioconf_backend.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
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
    @Basic
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "lokacijaByLokacijaId")
    private List<DogadjajEntity> dogadjaji;
    @OneToMany(mappedBy = "lokacijaByLokacijaId")
    private List<KonferencijaEntity> konferencije;
    @OneToMany(mappedBy = "lokacijaByLokacijaId")
    private List<ResursEntity> resursi;
    @OneToMany(mappedBy = "lokacijaByLokacijaId")
    private List<SobaEntity> sobe;

}
