package snt.repository;

import snt.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import snt.model.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllByUser(User user);
}
