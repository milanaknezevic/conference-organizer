package com.example.pisioconf_backend.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Dogadjaj {
    private Integer id;
    private Date startTime;
    private Date endTime;
    private String naziv;
    private String url;
}