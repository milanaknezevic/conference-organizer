package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.entities.LokacijaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LokacijaRepository extends JpaRepository<LokacijaEntity, Integer> {


}
