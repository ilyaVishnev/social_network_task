package snt.service;

import org.springframework.stereotype.Service;
import snt.model.Message;
import snt.model.User;
import snt.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAllMessagesByUser(User user) {
        return messageRepository.findAllByUser(user);
    }

    public void saveMessage(String message, User user) {
        messageRepository.save(new Message(message, user));
    }
}
