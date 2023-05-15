package com.example.pisioconf_backend.models.requests;

import com.example.pisioconf_backend.models.entities.KorisnikEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ChangeStatusRequest {

    @NotNull
    private KorisnikEntity.Status status;
}