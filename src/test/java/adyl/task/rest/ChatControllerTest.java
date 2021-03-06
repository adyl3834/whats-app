package adyl.task.rest;

import adyl.task.model.Account;
import adyl.task.model.Chat;
import adyl.task.model.Image;
import adyl.task.model.Massage;
import adyl.task.repository.AccountRepository;
import adyl.task.repository.ChatRepository;
import adyl.task.repository.MassageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static adyl.task.type.MassageType.MASSAGE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatControllerTest {
    private final String CHAT_NAME = "chat";
    @Autowired
    private ChatController chatController;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MassageRepository massageRepository;

    @AfterEach
    void tearDown() {
        massageRepository.deleteAll();
        chatRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    @DisplayName("/createChat rest api test")
    void save() {
        //act
        Chat chat = chatController.save(prepareChat());
        //assert
        assertNotNull(chat);
        assertEquals(CHAT_NAME, chat.getChatName());
    }

    @Test
    @DisplayName("/updateChat rest api test")
    void update() throws IOException {
        //prepare
        Chat chat = chatController.save(prepareChat());
        //act
        Chat updateChat = prepareChatWithImage(chat);
        //assert
        assertNotNull(updateChat);
        assertNotNull(updateChat.getChatPicture());
        assertEquals(chat.getId(), updateChat.getId());
    }

    @Test
    @DisplayName("/getChatById rest api test")
    void getChatById() {
        //prepare
        Chat chat = chatController.save(prepareChat());
        //act
        Chat chatById = chatController.getChatById(chat.getId());
        //assert
        assertNotNull(chatById);
        assertEquals(chat.getId(), chatById.getId());
    }

    @Test
    void getAllTheAccounts() {
        //prepare
        chatRepository.save(prepareChat());
        Chat secondChat = prepareChat();
        String UPDATE_CHAT_NAME = "updateChat";
        secondChat.setChatName(UPDATE_CHAT_NAME);
        chatRepository.save(secondChat);
        //act
        ArrayList<Chat> chats = (ArrayList<Chat>) chatController.getAllTheAccounts();
        //assert
        assertNotNull(chats);
        assertEquals(2, chats.size());

    }

    @Test
    @DisplayName("/delete{id} rest api test")
    void deleteById() {
        //prepare
        Chat chat = chatRepository.save(prepareChat());
        //act
        chatController.deleteById(chat.getId());
        Chat nullChat = chatRepository.getById(chat.getId());
        //assert
        assertNull(nullChat);
    }

    @Test
    @DisplayName("/getMassageById{id} rest api test")
    void getMassageById() {
        //prepare
        Massage massage = prepareMassage();
        Account account = accountRepository.save(prepareAccount());
        Chat chat = chatRepository.save(prepareChat());
        massage.setSender_id(account);
        massage.setChat_id(chat);
        massageRepository.save(massage);
        Massage massage2 = prepareMassage();
        massage2.setSender_id(account);
        massage2.setChat_id(chat);
        massageRepository.save(massage2);
        Massage massage3 = prepareMassage();
        massage3.setSender_id(account);
        massage3.setChat_id(chat);
        massageRepository.save(massage3);
        //act
        List<Massage> massages = chatController.getMassagesById(chat.getId());
        //assert
        assertNotNull(massage);
        assertFalse(massages.isEmpty());
        assertEquals(2, massages.size());

    }

    private Chat prepareChatWithImage(Chat chat) throws IOException {
        chat.setChatPicture(Image.builder().imageName(extractBytes()).build());
        return chat;
    }

    private byte[] extractBytes() throws IOException {
        // open image
        File imgPath = new File("/Users/a18517822/Desktop/0ae8f8ee-a602-4bf7-b9cd-1e669374a7a3.jpg");
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        return (data.getData());
    }

    private Chat prepareChat() {
        return Chat.builder().chatName(CHAT_NAME).build();
    }

    private Massage prepareMassage() {
        String MASSAGE_TEXT = "massage";
        return Massage.builder().massage(MASSAGE_TEXT).datetime(LocalDateTime.now()).massageType(MASSAGE).build();
    }

    private Account prepareAccount() {
        String ACCOUNT_NAME = "account";
        return Account.builder().name(ACCOUNT_NAME).build();
    }
}