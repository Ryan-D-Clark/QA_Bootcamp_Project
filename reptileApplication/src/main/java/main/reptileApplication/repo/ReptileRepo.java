package main.reptileApplication.repo;

import main.reptileApplication.entity.Reptile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReptileRepo extends JpaRepository<Reptile,Long> {
}
