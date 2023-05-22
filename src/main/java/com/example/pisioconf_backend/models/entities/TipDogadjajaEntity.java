package com.example.pisioconf_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    @OneToMany(mappedBy = "tipDogadjaja")
    private List<DogadjajEntity> dogadjajs;

}
