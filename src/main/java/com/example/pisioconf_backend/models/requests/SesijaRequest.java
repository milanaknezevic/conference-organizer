package com.example.pisioconf_backend.models.requests;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SesijaRequest {
    private Date startTime;
    private Date endTime;
    private Integer konferencijaId;
    private List<DogadjajRequest> dogadjaji;
}
