package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.entities.DogadjajEntity;
import com.example.pisioconf_backend.models.entities.KorisnikEntity;
import com.sun.jdi.connect.ListeningConnector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogadjajRepository extends JpaRepository<DogadjajEntity,Integer> {


    @Query("SELECT d FROM DogadjajEntity d WHERE d.korisnik.id =:moderatorId")
    List<DogadjajEntity> findALlDogadjajiByModerator(Integer moderatorId);

}
