package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.models.entities.OcjenaEntity;
import com.example.pisioconf_backend.models.entities.OcjenaEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcjenaRepository extends JpaRepository<OcjenaEntity, OcjenaEntityPK> {

    List<OcjenaEntity> findAllByKonferencijaByKonferencijaId(Integer id);
}
