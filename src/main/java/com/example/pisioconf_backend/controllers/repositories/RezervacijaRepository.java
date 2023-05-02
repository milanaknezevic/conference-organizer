package com.example.pisioconf_backend.controllers.repositories;

import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervacijaRepository extends JpaRepository<RezervacijaEntity,Integer> {
//n:m veza
}
