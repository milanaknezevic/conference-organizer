package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.models.entities.SobaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SobaRepository extends JpaRepository<SobaEntity,Integer> {

    List<SobaEntity>  findAllByLokacija_Id(Integer id);
}
