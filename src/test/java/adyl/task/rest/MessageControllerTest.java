package adyl.task.rest;

import adyl.task.model.Account;
import adyl.task.model.Chat;
import adyl.task.model.Message;
import adyl.task.model.Sticker;
import adyl.task.repository.AccountRepository;
import adyl.task.repository.ChatRepository;
import adyl.task.repository.MessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static adyl.task.type.MessageType.MASSAGE;
import static adyl.task.type.MessageType.STICKER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageControllerTest {
    private final String MASSAGE_TEXT = "massage";

    @Autowired
    private MessageController messageController;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ChatRepository chatRepository;

    @AfterEach
    void tearDown() {
        messageRepository.deleteAll();
        accountRepository.deleteAll();
        chatRepository.deleteAll();
    }

    @Test
    @DisplayName("/createWithJoin rest api test")
    void saveWithJoin() {
        //prepare
        Message message = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        message.setSenderId(account);
        message.setChatId(chat);
        //act
        Message save = messageController.save(message);
        //assert
        assertNotNull(save);
        assertEquals(MASSAGE_TEXT, message.getMassage());
        assertEquals(MASSAGE, save.getMessageType());
    }

    @Test
    @DisplayName("saveWithoutJoin rest api test")
    void saveWithoutJoin() {

        //act
        Exception ex = assertThrows(ConstraintViolationException.class, () -> messageController.save(prepareMassage()));
        //assert
        assertEquals(ex.getClass().getSimpleName(), "ConstraintViolationException");

    }

    @Test
    @DisplayName("/saveWithoutMassageReply rest api test")
    void saveWithoutMassageReply() {
        //prepare
        Message message = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        message.setSenderId(account);
        message.setChatId(chat);
        Message save = messageController.save(message);
        save.setMessageReply(save);
        //act
        messageController.save(save);
        //assert
        assertNotNull(save);
        assertEquals(MASSAGE_TEXT, message.getMassage());
        assertEquals(MASSAGE, save.getMessageType());
        assertEquals(save.getId(), save.getMessageReply().getId());
    }

    @Test
    @DisplayName("/saveWithMassageTypeSticker rest api test")
    void saveWithMassageTypeSticker() {
        //prepare
        Message message = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        message.setSenderId(account);
        message.setChatId(chat);
        message.setMessageType(STICKER);
        //act
        Throwable ex = assertThrows(NullPointerException.class, () -> messageController.save(message));
        //assert
        assertEquals(ex.getMessage(), "MassageType is STICKER, Massage must be sticker!");
    }

    @Test
    @DisplayName("/saveWithMassageTypeSticker rest api test")
    void saveWithMassageTypeMassage() {
        //prepare
        Message message = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        message.setSenderId(account);
        message.setChatId(chat);
        message.setMassage(null);
        message.setStickerId(Sticker.builder().build());
        //act
        Throwable ex = assertThrows(NullPointerException.class, () -> messageController.save(message));
        //assert
        assertEquals(ex.getMessage(), "MassageType is MASSAGE, Massage must be text!");
    }

    @Test
    @DisplayName("/update rest api test")
    void update() {
        //prepare
        Message message = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        message.setSenderId(account);
        message.setChatId(chat);
        Message save = messageRepository.save(message);
        String UPDATE_MASSAGE_TEXT = "updateMassage";
        save.setMassage(UPDATE_MASSAGE_TEXT);
        //act
        Message update = messageController.update(save);
        //assert
        assertNotNull(update);
        assertNotNull(update.getId());
        assertEquals(save.getId(), update.getId());
    }

    @Test
    @DisplayName("/getAllMassages rest api test")
    void getAllMassages() {
        //prepare
        Message message = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        message.setSenderId(account);
        message.setChatId(chat);
        messageRepository.save(message);

        Message secondMessage = prepareMassage();
        Account secondAccount = accountRepository.save(prepareAccount());
        Chat secondChat = chatRepository.save(prepareChat());
        secondMessage.setSenderId(secondAccount);
        secondMessage.setChatId(secondChat);
        messageRepository.save(secondMessage);
        //act
        ArrayList<Message> messages = (ArrayList<Message>) messageController.getAllMassages();
        //assert
        assertNotNull(messages);
        assertEquals(2, messages.size());
    }

    @Test
    @DisplayName("/delete{} rest api test")
    void delete() {
        //prepare
        Message message = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        message.setSenderId(account);
        message.setChatId(chat);
        Message save = messageRepository.save(message);
        //act
        messageController.delete(save.getId());
        //assert
        assertTrue(messageController.getAllMassages().isEmpty());
    }

    private Message prepareMassage() {
        return Message.builder().massage(MASSAGE_TEXT).datetime(LocalDateTime.now()).messageType(MASSAGE).build();
    }

    private Account prepareAccount() {
        String ACCOUNT_NAME = "account";
        return Account.builder().name(ACCOUNT_NAME).build();
    }

    private Chat prepareChat() {
        String CHAT_NAME = "chat";
        return Chat.builder().chatName(CHAT_NAME).build();
    }
}