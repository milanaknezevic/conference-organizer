package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tip_dogadjaja")
public class TipDogadjajaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(mappedBy = "tipDogadjajaByTipDogadjajaId")
    private List<DogadjajEntity> dogadjaji;

}
