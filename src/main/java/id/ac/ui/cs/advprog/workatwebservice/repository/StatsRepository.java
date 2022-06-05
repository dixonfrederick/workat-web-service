package id.ac.ui.cs.advprog.workatwebservice.repository;

import id.ac.ui.cs.advprog.workatwebservice.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatsRepository extends JpaRepository<Stats, String> {
    Optional<Stats> findById(String id);
}
