package com.example.pisioconf_backend.models.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class RezervacijaEntityPK implements Serializable {
    @Column(name = "RESURS_id")
    private Integer resursId;
    @Column(name = "DOGADJAJ_id")
    private Integer dogadjajId;

}
