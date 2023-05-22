package com.example.pisioconf_backend.models.requests;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class KonferencijaRequest {
    private Date startTime;
    private Date endTime;
    private String naziv;
    private Boolean status;
    private List<SesijaRequest> sesije;
    private Integer lokacijaId;
    private Integer organizatorId;
}
