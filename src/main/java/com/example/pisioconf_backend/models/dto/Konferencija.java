package com.example.pisioconf_backend.models.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Konferencija {
    private Integer id;
    private Date startTime;
    private Date endTime;
    private String naziv;
    private Boolean status;
    private String url;
    private List<Dogadjaj> dogadjajs;
    private Lokacija lokacija;


}
