package com.example.pisioconf_backend.repositories;

import com.example.pisioconf_backend.models.entities.DogadjajEntity;
import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.entities.KorisnikEntity;
import com.example.pisioconf_backend.models.enums.Role;
import com.example.pisioconf_backend.models.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<KorisnikEntity, Integer> {
    @Query("SELECT k from KonferencijaEntity  k where k.korisnik.id =:idk")
    List<KonferencijaEntity> getAllByKorisnikByModeratorId(Integer idk);

    @Query("SELECT k from KonferencijaEntity  k where k.korisnik.id =:id")
    List<KonferencijaEntity> getAllByKorisnikByOrganizatorId(Integer id);

    //Optional<KorisnikEntity> findByKorisnickoImeAndStatus(String username,KorisnikEntity.Status status);
    Optional<KorisnikEntity> findByUsernameAndStatus(String username, KorisnikEntity.Status status);

    Boolean existsByUsername(String username);

    List<KorisnikEntity> findAllByStatusAndRolaNotOrderByNazivAsc(KorisnikEntity.Status status,Role rola );
    @Query("SELECT k FROM KorisnikEntity k WHERE k.id <> :currentUserId")
    List<KorisnikEntity> findAllExceptCurrent(Integer currentUserId);

    Boolean existsByUsernameAndIdNot(String username,Integer id);

    List<KorisnikEntity> findAllByRolaAndStatus(Role role,KorisnikEntity.Status status);


}
