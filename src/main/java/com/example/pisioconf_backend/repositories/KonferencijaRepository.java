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


    @Query("SELECT k FROM KonferencijaEntity k WHERE k.status = :status AND k.startTime >= :datum")
    List<KonferencijaEntity> findAllKonferencijeByStatusAndDatum(Boolean status, LocalDateTime datum);
    @Query("SELECT k FROM KonferencijaEntity k WHERE k.status = :status AND k.naziv LIKE :namePrefix%")
    List<KonferencijaEntity> findAllKonferencijeByStatusAndNaziv(Boolean status, @Param("namePrefix") String namePrefix) throws NotFoundException;

    @Query("SELECT k FROM KonferencijaEntity k WHERE k.startTime >= :datum AND k.naziv LIKE :namePrefix%")
    List<KonferencijaEntity> findAllKonferencijeByDatumAndNaziv(LocalDateTime datum,@Param("namePrefix") String namePrefix) throws NotFoundException;
    @Query("SELECT k FROM KonferencijaEntity k WHERE k.status = :status AND k.startTime >= :datum AND k.naziv LIKE :namePrefix%")
    List<KonferencijaEntity> findAllKonferencijeByStatusAndDatumAndNaziv(Boolean status, LocalDateTime datum, @Param("namePrefix") String namePrefix) throws NotFoundException;
}