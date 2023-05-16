package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.entities.OcjenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
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

    @Query("select k from KonferencijaEntity  k where k.status=: status")
    List<KonferencijaEntity> findAllKonferencijeByStatus(Boolean status);

    @Query("SELECT k FROM KonferencijaEntity k WHERE k.startTime >= :datum")
    List<KonferencijaEntity> findAllKonferencijeByDatum(LocalDateTime datum);

    @Query("SELECT k FROM KonferencijaEntity k WHERE k.naziv LIKE :namePrefix%")
    List<KonferencijaEntity> findAllKonferencijeByNaziv(@Param("namePrefix") String namePrefix) throws NotFoundException;
}