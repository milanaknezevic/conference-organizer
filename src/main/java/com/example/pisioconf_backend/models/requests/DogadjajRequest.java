package com.example.pisioconf_backend.models.requests;

import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DogadjajRequest {
    private Date startTime;
    private Date endTime;
    private String naziv;
    private String url;
    private Integer konferencijaId;
    private Integer tipDogadjaja;
    private Integer lokacijaId;
    private Integer sobaId;
   // private List<Integer> resursi;
    private Integer moderator_Id;
    //private List<RezervacijaRequest> rezervacijas;
}
