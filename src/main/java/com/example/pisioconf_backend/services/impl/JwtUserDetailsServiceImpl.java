package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.models.dto.JwtUser;
import com.example.pisioconf_backend.models.entities.KorisnikEntity;
import com.example.pisioconf_backend.repositories.KorisnikRepository;
import com.example.pisioconf_backend.services.JwtUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    private final ModelMapper modelMapper;
    private final KorisnikRepository korisnikRepository;

    public JwtUserDetailsServiceImpl(ModelMapper modelMapper, KorisnikRepository korisnikRepository) {
        this.modelMapper = modelMapper;
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return modelMapper.map(korisnikRepository.findByUsernameAndStatus(username, KorisnikEntity.Status.ACTIVE).
                orElseThrow(() -> new UsernameNotFoundException(username)), JwtUser.class);
    }
}
