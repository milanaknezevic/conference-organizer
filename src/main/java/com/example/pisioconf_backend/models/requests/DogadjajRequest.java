package com.example.pisioconf_backend.models.requests;

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
    private List<Integer> resursi;
    private Integer moderator_Id;
}
