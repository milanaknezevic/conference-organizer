package com.example.pisioconf_backend.models.entities;

import com.example.pisioconf_backend.models.dto.Dogadjaj;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "konferencija")
public class KonferencijaEntity {
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
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @Basic
    @Column(name = "status")
    private Boolean status;
    @Basic
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "organizator_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @ManyToOne
    @JoinColumn(name = "LOKACIJA_id", referencedColumnName = "id")
    private LokacijaEntity lokacija;
    @OneToMany(mappedBy = "konferencija")
    @Cascade(CascadeType.DELETE)
    private List<OcjenaEntity> ocjenas;
    @OneToMany(mappedBy = "konferencija")
    @Cascade(CascadeType.DELETE)
    private List<DogadjajEntity> dogadjajs;
}
