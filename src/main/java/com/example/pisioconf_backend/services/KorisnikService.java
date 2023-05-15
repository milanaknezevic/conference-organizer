package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Korisnik;
import com.example.pisioconf_backend.models.dto.LoginResponse;
import com.example.pisioconf_backend.models.entities.KorisnikEntity;
import com.example.pisioconf_backend.models.requests.ChangeRoleRequest;
import com.example.pisioconf_backend.models.requests.ChangeStatusRequest;
import com.example.pisioconf_backend.models.requests.SignUpRequest;
import com.example.pisioconf_backend.models.requests.UserUpdateRequest;

import java.util.List;


public interface KorisnikService {

    KorisnikEntity findById(Integer id);
    //update
    //deleteUser
    List<Konferencija> getKonferencijeZaModeratora(Integer idModeratora);
    List<Konferencija> getKonferencijeZaOrganizatora(Integer idOrganizatora);

    LoginResponse findById(Integer id, Class<LoginResponse> response) throws NotFoundException;
    Korisnik insert(KorisnikEntity korisnikEntity, Class<Korisnik> korisnik) throws NotFoundException;

    void signUp(SignUpRequest request);

    void changeStatus(Integer userId, ChangeStatusRequest request) throws Exception;

    void changeRole(Integer userId, ChangeRoleRequest request);

    Korisnik update(Integer id, UserUpdateRequest user) throws Exception;



}
