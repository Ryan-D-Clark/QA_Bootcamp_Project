package main.reptileApplication.repo;

import main.reptileApplication.entity.Reptile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReptileRepo extends JpaRepository<Reptile,Long> {
}
