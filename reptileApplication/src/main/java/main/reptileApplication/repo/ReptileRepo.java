package main.reptileApplication.repo;

import main.reptileApplication.entity.Reptile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReptileRepo extends JpaRepository<Reptile,Long> {

    @Query(value = "SELECT * from reptile WHERE name = ?1", nativeQuery = true)
    List<Reptile> findByName(String name);

    @Query(value = "SELECT * from reptile WHERE specie = ?1", nativeQuery = true)
    List<Reptile> findBySpecie(String specie);

}
