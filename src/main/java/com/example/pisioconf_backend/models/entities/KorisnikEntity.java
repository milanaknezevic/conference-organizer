package com.example.pisioconf_backend.models.entities;

import com.example.pisioconf_backend.models.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "korisnik")
public class KorisnikEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @Basic
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rola", nullable = false)
    private Role rola;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private Status status;
    @OneToMany(mappedBy = "korisnik")
    private List<DogadjajEntity> dogadjajs;
    @OneToMany(mappedBy = "korisnik")
    private List<KonferencijaEntity> konferencijas;
    @OneToMany(mappedBy = "korisnik")
    private List<OcjenaEntity> ocjenas;
    @OneToMany(mappedBy = "korisnik")
    private List<PosjetilacEntity> posjetilacs;
    public enum Status {
        REQUESTED, ACTIVE, BLOCKED
    }
}
