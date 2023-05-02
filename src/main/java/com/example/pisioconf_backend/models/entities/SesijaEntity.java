package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "sesija")
public class SesijaEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "start_time")
    private Date startTime;
    @Basic
    @Column(name = "end_time")
    private Date endTime;
    @OneToMany(mappedBy = "sesijaBySesijaId", cascade = CascadeType.ALL)
    private List<DogadjajEntity> dogadjaji;
    @ManyToOne
    @JoinColumn(name = "konferencija_id", referencedColumnName = "id", nullable = false)
    private KonferencijaEntity konferencijaByKonferencijaId;

}
