package adyl.task.rest;

import adyl.task.model.Account;
import adyl.task.model.Image;
import adyl.task.model.Sticker;
import adyl.task.model.StickerPack;
import adyl.task.repository.AccountRepository;
import adyl.task.repository.ImageRepository;
import adyl.task.repository.StickerPackRepository;
import adyl.task.repository.StickerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StickerControllerTest {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private StickerController stickerController;
    @Autowired
    private StickerRepository stickerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StickerPackRepository stickerPackRepository;

    @AfterEach
    void tearDown() {
        stickerRepository.deleteAll();
        imageRepository.deleteAll();
        stickerPackRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void save() throws IOException {
        //prepare StickerPack
        Account owner = accountRepository.save(prepareAccount());
        StickerPack stickerPack = prepareStickerPack();
        stickerPack.setOwnerId(owner);
        StickerPack pack = stickerPackRepository.save(stickerPack);
        //prepare Image
        Image image = imageRepository.save(prepareImage());
        //prepare Sticker to save
        Sticker sticker = prepareSticker(image, pack);
        //act
        Sticker save =  stickerController.save(sticker);
        //assert
        assertNotNull(save);
        assertNotNull(save.getId());
        assertNotNull(save.getStickerImage());
        assertNotNull(save.getStickerPackId());
    }


    @Test
    void getAllTheAccounts() throws IOException {
        //prepare StickerPack
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
        List<Sticker> stickers = stickerController.getAllStickers();
        //assert
        Assertions.assertNotNull(sticker);
        assertFalse(stickers.isEmpty());
        assertEquals(1, stickers.size());
    }

    @Test
    void delete() throws IOException {
        //prepare StickerPack
        Account owner = accountRepository.save(prepareAccount());
        StickerPack stickerPack = prepareStickerPack();
        stickerPack.setOwnerId(owner);
        StickerPack pack = stickerPackRepository.save(stickerPack);
        //prepare Image
        Image image = imageRepository.save(prepareImage());
        //prepare Sticker to save
        Sticker sticker = prepareSticker(image, pack);
        Sticker save =  stickerRepository.save(sticker);
        //act
        stickerController.delete(save.getId());
        //assert
        assertTrue(stickerController.getAllStickers().isEmpty());

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