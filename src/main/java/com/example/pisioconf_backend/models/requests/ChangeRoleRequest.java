package com.example.pisioconf_backend.models.requests;

import com.example.pisioconf_backend.models.enums.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangeRoleRequest {

    @NotNull
    private Role role;
}