package adyl.task.rest;

import adyl.task.model.Account;
import adyl.task.model.Chat;
import adyl.task.model.Massage;
import adyl.task.model.Sticker;
import adyl.task.repository.AccountRepository;
import adyl.task.repository.ChatRepository;
import adyl.task.repository.MassageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static adyl.task.type.MassageType.MASSAGE;
import static adyl.task.type.MassageType.STICKER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MassageControllerTest {
    private final String MASSAGE_TEXT = "massage";

    @Autowired
    private MassageController massageController;
    @Autowired
    private MassageRepository massageRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ChatRepository chatRepository;

    @AfterEach
    void tearDown() {
        massageRepository.deleteAll();
        accountRepository.deleteAll();
        chatRepository.deleteAll();
    }

    @Test
    @DisplayName("/createWithJoin rest api test")
    void saveWithJoin() {
        //prepare
        Massage massage = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        massage.setSender_id(account);
        massage.setChat_id(chat);
        //act
        Massage save = massageController.save(massage);
        //assert
        assertNotNull(save);
        assertEquals(MASSAGE_TEXT, massage.getMassage());
        assertEquals(MASSAGE, save.getMassageType());
    }

    @Test
    @DisplayName("saveWithoutJoin rest api test")
    void saveWithoutJoin() {

        //act
        Exception ex = assertThrows(ConstraintViolationException.class, () -> massageController.save(prepareMassage()));
        //assert
        assertEquals(ex.getClass().getSimpleName(), "ConstraintViolationException");

    }

    @Test
    @DisplayName("/saveWithoutMassageReply rest api test")
    void saveWithoutMassageReply() {
        //prepare
        Massage massage = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        massage.setSender_id(account);
        massage.setChat_id(chat);
        Massage save = massageController.save(massage);
        save.setMassage_reply(save);
        //act
        massageController.save(save);
        //assert
        assertNotNull(save);
        assertEquals(MASSAGE_TEXT, massage.getMassage());
        assertEquals(MASSAGE, save.getMassageType());
        assertEquals(save.getId(), save.getMassage_reply().getId());
    }

    @Test
    @DisplayName("/saveWithMassageTypeSticker rest api test")
    void saveWithMassageTypeSticker() {
        //prepare
        Massage massage = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        massage.setSender_id(account);
        massage.setChat_id(chat);
        massage.setMassageType(STICKER);
        //act
        Throwable ex = assertThrows(NullPointerException.class, () -> massageController.save(massage));
        //assert
        assertEquals(ex.getMessage(), "MassageType is STICKER, Massage must be sticker!");
    }

    @Test
    @DisplayName("/saveWithMassageTypeSticker rest api test")
    void saveWithMassageTypeMassage() {
        //prepare
        Massage massage = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        massage.setSender_id(account);
        massage.setChat_id(chat);
        massage.setMassage(null);
        massage.setSticker_id(Sticker.builder().build());
        //act
        Throwable ex = assertThrows(NullPointerException.class, () -> massageController.save(massage));
        //assert
        assertEquals(ex.getMessage(), "MassageType is MASSAGE, Massage must be text!");
    }

    @Test
    @DisplayName("/update rest api test")
    void update() {
        //prepare
        Massage massage = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        massage.setSender_id(account);
        massage.setChat_id(chat);
        Massage save = massageRepository.save(massage);
        String UPDATE_MASSAGE_TEXT = "updateMassage";
        save.setMassage(UPDATE_MASSAGE_TEXT);
        //act
        Massage update = massageController.update(save);
        //assert
        assertNotNull(update);
        assertNotNull(update.getId());
        assertEquals(save.getId(), update.getId());
    }

    @Test
    @DisplayName("/getAllMassages rest api test")
    void getAllMassages() {
        //prepare
        Massage massage = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        massage.setSender_id(account);
        massage.setChat_id(chat);
        massageRepository.save(massage);

        Massage secondMassage = prepareMassage();
        Account secondAccount = accountRepository.save(prepareAccount());
        Chat secondChat = chatRepository.save(prepareChat());
        secondMassage.setSender_id(secondAccount);
        secondMassage.setChat_id(secondChat);
        massageRepository.save(secondMassage);
        //act
        ArrayList<Massage> massages = (ArrayList<Massage>) massageController.getAllMassages();
        //assert
        assertNotNull(massages);
        assertEquals(2, massages.size());
    }

    @Test
    @DisplayName("/delete{} rest api test")
    void delete() {
        //prepare
        Massage massage = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        massage.setSender_id(account);
        massage.setChat_id(chat);
        Massage save = massageRepository.save(massage);
        //act
        massageController.delete(save.getId());
        //assert
        assertTrue(massageController.getAllMassages().isEmpty());
    }

    private Massage prepareMassage() {
        return Massage.builder().massage(MASSAGE_TEXT).datetime(LocalDateTime.now()).massageType(MASSAGE).build();
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