package id.ac.ui.cs.advprog.workatwebservice.repository;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameObject, String> {
    GameObject findByGameId(String id);
}