package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.entities.KorisnikEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<KorisnikEntity, Integer> {
    @Query("SELECT k from KonferencijaEntity  k where k.korisnikByModeratorId.id =:idk")
    List<KonferencijaEntity> getAllByKorisnikByModeratorId(Integer idk);

    @Query("SELECT k from KonferencijaEntity  k where k.korisnikByOrganizatorId.id =:id")
    List<KonferencijaEntity> getAllByKorisnikByOrganizatorId(Integer id);

    //Optional<KorisnikEntity> findByKorisnickoImeAndStatus(String username,KorisnikEntity.Status status);
    Optional<KorisnikEntity> findByUsernameAndStatus(String username, KorisnikEntity.Status status);

    Boolean existsByUsername(String username);

    Boolean existsByUsernameAndIdNot(String username,Integer id);
}
