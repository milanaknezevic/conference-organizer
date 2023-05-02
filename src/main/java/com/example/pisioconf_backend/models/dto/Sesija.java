package com.example.pisioconf_backend.models.dto;

import com.example.pisioconf_backend.models.dto.Dogadjaj;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Sesija {
    private Integer id;
    private List<Dogadjaj> dogadjaji;
    private Date startTime;
    private Date endTime;
}
