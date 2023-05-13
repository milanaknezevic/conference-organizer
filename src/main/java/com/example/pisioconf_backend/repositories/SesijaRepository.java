package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.models.entities.SesijaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesijaRepository extends JpaRepository<SesijaEntity,Integer> {

    //ovako bih mogla dobiti sve konferencije by sesija id
    //List<SesijaEntity> getAllBySesije_id (Integer id);


}
