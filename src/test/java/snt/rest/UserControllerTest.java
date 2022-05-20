package snt.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import snt.model.Message;
import snt.model.User;
import snt.model.UserMessage;
import snt.service.MessageService;
import snt.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private MessageService messageService;

    @Mock
    private UserService userService;

    private UserMessage userMessage;

    private User user;

    @BeforeEach
    public void init() {
        userMessage = new UserMessage();
        userMessage.setName("user1");
        userMessage.setMessage("message");
        user = new User();
        user.setName("user1");
    }

    @Test
    public void getMessages_findTenMessages_SpecialMessageTest() {
        userMessage.setMessage("history 10");
        when(userService.getByLogin("user1")).thenReturn(user);
        when(messageService.findAllMessagesByUser(user)).thenReturn(Arrays.asList(new Message("1", user),
                new Message("2", user), new Message("3", user), new Message("4", user), new Message("5", user),
                new Message("6", user), new Message("7", user), new Message("8", user), new Message("9", user),
                new Message("10", user), new Message("11", user)));
        List<String> messages = userController.getMessages(userMessage);
        assertEquals(10, messages.size());
    }

    @Test
    public void getMessages_saveMessage_NotSpecialMessageTest() {
        when(userService.getByLogin("user1")).thenReturn(user);
        doNothing().when(messageService).saveMessage("message", user);
        userController.getMessages(userMessage);
        verify(messageService, times(1)).saveMessage("message", user);
    }
}
