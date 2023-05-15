package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.ForbiddenException;
import com.example.pisioconf_backend.models.dto.JwtUser;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Korisnik;
import com.example.pisioconf_backend.models.requests.ChangeRoleRequest;
import com.example.pisioconf_backend.models.requests.ChangeStatusRequest;
import com.example.pisioconf_backend.models.requests.UserUpdateRequest;
import com.example.pisioconf_backend.services.KorisnikService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/korisnici")
public class KorisnikController {


    public final KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping("/{id}/moderator_konferencije")
    public List<Konferencija> getKonferencijeZaModeratora(@PathVariable Integer id) {
        return korisnikService.getKonferencijeZaModeratora(id);
    }
    @GetMapping("/{id}organizator_konferencije")
    public List<Konferencija> getKonferencijeZaOrganizatora(@PathVariable Integer id)
    {
        return korisnikService.getKonferencijeZaOrganizatora(id);
    }

    @PutMapping("/{id}")
    public Korisnik update(@PathVariable Integer id, @Valid @RequestBody UserUpdateRequest request, Authentication auth) throws Exception {
        JwtUser user=(JwtUser)auth.getPrincipal();
        if(!user.getId().equals(id))
        {
            throw new ForbiddenException();
        }
        return korisnikService.update(id,request);
    }
    @PatchMapping("/{id}/status")
    public void changeStatus(@PathVariable Integer id, @RequestBody @Valid ChangeStatusRequest request, Authentication auth) throws Exception {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (jwtUser.getId().equals(id))
            throw new ForbiddenException();
        korisnikService.changeStatus(id, request);
    }

    @PatchMapping("/{id}/role")
    public void changeRole(@PathVariable Integer id, @RequestBody @Valid ChangeRoleRequest request, Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (jwtUser.getId().equals(id))
            throw new ForbiddenException();
        korisnikService.changeRole(id, request);
    }

}
