package com.example.pisioconf_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "sesija")
public class SesijaEntity {
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
    @OneToMany(mappedBy = "sesija")
    private List<DogadjajEntity> dogadjajs;
    @ManyToOne
    @JoinColumn(name = "konferencija_id", referencedColumnName = "id", nullable = false)
    private KonferencijaEntity konferencija;

}
