package id.ac.ui.cs.advprog.workatwebservice.repository;

import id.ac.ui.cs.advprog.workatwebservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(String id);
}