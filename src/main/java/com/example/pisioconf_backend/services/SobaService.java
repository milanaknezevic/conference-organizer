package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.models.dto.Soba;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface SobaService  {
    List<Soba> findAllByLokacijaId(Integer lokacija);
}
