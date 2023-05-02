package com.example.pisioconf_backend.models.requests;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SesijaRequest {
    private List<DogadjajRequest> dogadjaji;
    private Date startTime;
    private Date endTime;
}
