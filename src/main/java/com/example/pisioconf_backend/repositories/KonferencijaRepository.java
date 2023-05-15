package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.entities.OcjenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KonferencijaRepository extends JpaRepository<KonferencijaEntity, Integer> {


    @Query("SELECT k from KonferencijaEntity  k where k.lokacijaByLokacijaId.id=:idk")
    List<KonferencijaEntity> getAllByLokgacijaByLokacijaId_Id(Integer idk);


    @Query("select o from OcjenaEntity  o where o.korisnikByKorisnikId.id=:id")
    List<OcjenaEntity> getAllOcjeneByKorisnikId(Integer id);

    @Query("select k from KonferencijaEntity k where k.status=false")
    List<KonferencijaEntity> getAllNotFinishedKonferencije();
    @Query("select k from KonferencijaEntity k where k.korisnikByModeratorId.id=:id")
    List<KonferencijaEntity> findAllKonferencijeByModeratorId(Integer id);

}