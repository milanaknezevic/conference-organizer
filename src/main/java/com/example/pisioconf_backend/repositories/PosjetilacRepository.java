package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.models.entities.PosjetilacEntity;
import com.example.pisioconf_backend.models.entities.PosjetilacEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosjetilacRepository extends JpaRepository<PosjetilacEntity,PosjetilacEntityPK> {

}
