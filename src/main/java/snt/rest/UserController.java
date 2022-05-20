package snt.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import snt.model.Message;
import snt.model.User;
import snt.model.UserMessage;
import snt.service.MessageService;
import snt.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService service;
    private MessageService messageService;

    public UserController(UserService service, MessageService messageService) {
        this.service = service;
        this.messageService = messageService;
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<User> getAll() {
        return this.service.getAll();
    }

    @GetMapping(path = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<String> getMessages(@RequestBody UserMessage userMessage) {
        if (userMessage.getMessage().equals("history 10")) {
            return messageService.findAllMessagesByUser(service.getByLogin(userMessage.getName()))
                    .stream().map(m -> m.getText()).limit(10).collect(Collectors.toList());
        }
        messageService.saveMessage(userMessage.getMessage(), service.getByLogin(userMessage.getName()));
        return new ArrayList<>();
    }
}
