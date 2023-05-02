package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @Column(name = "rola")
    private Integer rola;
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
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "korisnikByModeratorId")
    private List<KonferencijaEntity> konferencijeModeratora;
    @OneToMany(mappedBy = "korisnikByOrganizatorId")
    private List<KonferencijaEntity> konferencijeOrganizatora;
    @OneToMany(mappedBy = "korisnikByKorisnikId")
    private List<OcjenaEntity> ocjene;
    @OneToMany(mappedBy = "korisnikByKorisnikId")
    private List<PosjetilacEntity> posjetioci;

}
