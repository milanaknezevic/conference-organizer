package com.example.pisioconf_backend.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class RezervacijaEntityPK implements Serializable {
    @Column(name = "RESURS_id")
    private Integer resursId;
    @Column(name = "DOGADJAJ_id")
    private Integer dogadjajId;

}
