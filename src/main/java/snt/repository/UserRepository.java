package snt.repository;

import snt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findAll();

    Optional<User>findByName(String name);
}
