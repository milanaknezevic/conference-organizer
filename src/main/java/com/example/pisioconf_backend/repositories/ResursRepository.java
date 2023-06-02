package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.entities.ResursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResursRepository extends JpaRepository<ResursEntity,Integer> {

    List<ResursEntity> findAllByLokacija_Id(Integer idLokacije) throws NotFoundException;
}
