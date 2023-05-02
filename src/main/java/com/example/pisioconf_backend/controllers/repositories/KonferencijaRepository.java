package com.example.pisioconf_backend.controllers.repositories;

import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KonferencijaRepository extends JpaRepository<KonferencijaEntity, Integer> {


    @Query("SELECT k from KonferencijaEntity  k where k.lokacijaByLokacijaId.id=:idk")
    List<KonferencijaEntity> getAllByLokgacijaByLokacijaId_Id(Integer idk);

}