package adyl.task.rest;

import adyl.task.model.Account;
import adyl.task.model.Image;
import adyl.task.model.Sticker;
import adyl.task.model.StickerPack;
import adyl.task.repository.AccountRepository;
import adyl.task.repository.ImageRepository;
import adyl.task.repository.StickerPackRepository;
import adyl.task.repository.StickerRepository;
import com.google.common.collect.ImmutableSet;
import liquibase.pro.packaged.A;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StickerPackControllerTest {
    private final String STICKER_PACK_NAME = "pack";
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StickerPackController stickerPackController;
    @Autowired
    private StickerPackRepository stickerPackRepository;
    @Autowired
    private StickerRepository stickerRepository;
    @Autowired
    private ImageRepository imageRepository;

    @AfterEach
    void tearDown() {
        stickerRepository.deleteAll();
        stickerPackRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    @DisplayName("/save rest api test")
    void save() {
        //prepare
        Account owner = accountRepository.save(prepareAccount());
        StickerPack stickerPack = prepareStickerPack();
        stickerPack.setOwnerId(owner);
        //act
        StickerPack save = stickerPackController.save(stickerPack);
        //assert
        assertNotNull(save);
        assertEquals(STICKER_PACK_NAME, save.getPackName());
    }

    @Test
    @DisplayName("/update rest api test")
    void update() {
        //prepare
        Account owner = accountRepository.save(prepareAccount());
        Account first = accountRepository.save(prepareAccount());
        Account second = accountRepository.save(prepareAccount());
        StickerPack stickerPack = prepareStickerPack();
        stickerPack.setOwnerId(owner);
        stickerPack.setAccounts(ImmutableSet.of(first, second));
        //act
        StickerPack save = stickerPackController.save(stickerPack);
        //assert
        assertNotNull(save);
        assertNotNull(save.getAccounts());
        assertEquals(2, save.getAccounts().size());
        assertEquals(STICKER_PACK_NAME, save.getPackName());
    }

    @Test
    @DisplayName("/getAllTheAccounts rest api test")
    void getAllTheAccounts() {
        //prepare
        Account owner = accountRepository.save(prepareAccount());
        StickerPack stickerPack = prepareStickerPack();
        stickerPack.setOwnerId(owner);
        stickerPackRepository.save(stickerPack);

        Account account = accountRepository.save(prepareAccount());
        StickerPack sticker = prepareStickerPack();
        sticker.setOwnerId(account);
        stickerPackRepository.save(sticker);
        //act
        List<StickerPack> stickerPacks = stickerPackController.getAllStickerPacks();
        //assert
        assertNotNull(stickerPacks);
        assertFalse(stickerPacks.isEmpty());
        assertEquals(2, stickerPacks.size());

    }

    @Test
    @DisplayName("/getAllStickers rest api test")
    void getAllStickers() throws IOException {
        //prepare Sticker
        Account owner = accountRepository.save(prepareAccount());
        StickerPack stickerPack = prepareStickerPack();
        stickerPack.setOwnerId(owner);
        StickerPack pack = stickerPackRepository.save(stickerPack);
        //prepare Image
        Image image = imageRepository.save(prepareImage());
        //prepare Sticker to save
        Sticker sticker = prepareSticker(image, pack);
        stickerRepository.save(sticker);
        //act
        Set<Sticker> stickers = stickerPackController.getAllStickers(pack.getId());
        assertNotNull(stickers);
        assertFalse(stickers.isEmpty());
        assertEquals(1, stickers.size());
    }

    @Test
    @DisplayName("/delete{id} rest api test")
    void delete() {
        //prepare
        Account owner = accountRepository.save(prepareAccount());
        StickerPack stickerPack = prepareStickerPack();
        stickerPack.setOwnerId(owner);
        StickerPack save = stickerPackRepository.save(stickerPack);
        //act
        stickerPackController.delete(save.getId());
        //assert
        assertTrue(stickerPackController.getAllStickerPacks().isEmpty());
    }

    private Sticker prepareSticker(Image image, StickerPack pack) {
        return Sticker.builder().stickerImage(image).stickerPackId(pack).build();
    }

    private Account prepareAccount() {
        String ACCOUNT_NAME = "account";
        return Account.builder().name(ACCOUNT_NAME).build();
    }

    private StickerPack prepareStickerPack() {
        String STICKER_PACK_NAME = "pack";
        return StickerPack.builder().packName(STICKER_PACK_NAME).build();
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

    private Image prepareImage() throws IOException {
        return Image.builder().imageName(extractBytes()).data(LocalDate.now()).build();
    }
}