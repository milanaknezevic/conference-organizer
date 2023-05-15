package com.example.pisioconf_backend.models.entities;

import com.example.pisioconf_backend.models.enums.Role;
import lombok.Data;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "korisnikByModeratorId")
    private List<KonferencijaEntity> konferencijeModeratora;
    @OneToMany(mappedBy = "korisnikByOrganizatorId")
    private List<KonferencijaEntity> konferencijeOrganizatora;
    @OneToMany(mappedBy = "korisnikByKorisnikId")
    private List<OcjenaEntity> ocjene;
    @OneToMany(mappedBy = "korisnikByKorisnikId")
    private List<PosjetilacEntity> posjetioci;

    public enum Status {
        REQUESTED, ACTIVE, BLOCKED
    }
}
