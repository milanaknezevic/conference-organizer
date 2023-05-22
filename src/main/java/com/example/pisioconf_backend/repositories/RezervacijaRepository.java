package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.entities.RezervacijaEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RezervacijaRepository extends JpaRepository<RezervacijaEntity, RezervacijaEntityPK> {
    @Query("select r from RezervacijaEntity r where r.dogadjaj.id=:id")
    List<RezervacijaEntity> getAllRezervacijeByDogadjajID(Integer id);
}
