package adyl.task.rest;


import adyl.task.model.Account;
import adyl.task.model.Chat;
import adyl.task.model.Image;
import adyl.task.repository.AccountRepository;
import adyl.task.repository.ChatRepository;
import adyl.task.repository.ImageRepository;
import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {
    private final String ACCOUNT_NAME = "account";
    private final String UPDATE_ACCOUNT_NAME = "updateAccount";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AccountController accountController;
    @Autowired
    private ChatRepository chatRepository;

    @AfterEach
    void tearDown() {
        imageRepository.deleteAll();
        accountRepository.deleteAll();
        chatRepository.deleteAll();
    }


    @Test
    @DisplayName("/login rest api test")
    void login() {
        //prepare
        accountRepository.save(prepareAccount());
        //act
        URI targetUrl = UriComponentsBuilder.fromUriString("/whatsapp/account/login")
                .queryParam("name", ACCOUNT_NAME)
                .build()
                .encode()
                .toUri();

        Account account = this.restTemplate.getForObject(targetUrl, Account.class);
        //assert
        assertNotNull(account);
        assertEquals(ACCOUNT_NAME, account.getName());
    }

    @Test
    @DisplayName("/createAccount rest api test")
    void save() throws IOException {
        //act
        Account account = accountController.save(prepareAccountWithImage());
        //assert
        assertNotNull(account);
        assertEquals(ACCOUNT_NAME, account.getName());
    }


    @Test
    @DisplayName("/updateAccount rest api test")
    void update() {
        //prepare
        Account account = prepareAccount();
        account.setName(UPDATE_ACCOUNT_NAME);
        accountRepository.save(account);
        //act
        Account updateAccount = accountController.update(account);
        //assert
        assertNotNull(updateAccount);
        assertEquals(UPDATE_ACCOUNT_NAME, updateAccount.getName());
    }

    @Test
    @DisplayName("/getAllTheAccounts rest api test")
    void getAllTheAccounts() {
        //prepare
        Account account = prepareAccount();
        accountRepository.save(account);
        Account prepareAccount = prepareAccount();
        prepareAccount.setName(UPDATE_ACCOUNT_NAME);
        accountRepository.save(prepareAccount);
        //act
        ArrayList<Account> accounts = (ArrayList<Account>) accountController.getAllTheAccounts();
        //assert
        assertNotNull(accounts);
        assertEquals(2, accounts.size());
    }

    @Test
    @DisplayName("/getAllTheChats rest api test")
    void getAllTheChats() {
        // prepare
        Account account = prepareAccount();
        Chat second = chatRepository.save(prepareChat());
        account.setChats(ImmutableSet.of(prepareChat(), prepareChat()));
        accountRepository.save(account);
        // act
        Set<Chat> chats = accountController.getAllTheChats(account.getId());
        //assert
        assertNotNull(chats);
        assertFalse(chats.isEmpty());
        assertEquals(2, chats.size());
    }

    @Test
    @DisplayName("/delete{id} rest api test")
    void delete() {
        //prepare
        Account account = accountRepository.save(prepareAccount());
        //act
        accountController.delete(account.getId());
        Account accountById = accountRepository.findAccountById(account.getId());
        //assert
        assertNull(accountById);
    }

    @Test
    @DisplayName("/getAccountById rest api test")
    void getAccountById() {
        //prepare
        Account account = accountRepository.save(prepareAccount());
        //act
        Account accountById = accountController.getAccountById(account.getId());
        //assert
        assertNotNull(accountById);
        assertEquals(ACCOUNT_NAME, accountById.getName());
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

    private Account prepareAccountWithImage() throws IOException {
        byte[] image = extractBytes();
        return Account.builder().name(ACCOUNT_NAME).accountPicture(Image.builder().imageName(image).data(LocalDate.now()).build()).build();
    }

    private Account prepareAccount() {
        return Account.builder().name(ACCOUNT_NAME).build();
    }

    private Chat prepareChat() {
        return Chat.builder().chatName("Chat").build();
    }
}