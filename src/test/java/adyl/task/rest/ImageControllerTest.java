package adyl.task.rest;

import adyl.task.model.Image;
import adyl.task.repository.ImageRepository;
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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ImageControllerTest {

    @Autowired
    private ImageController imageController;
    @Autowired
    private ImageRepository imageRepository;


    @AfterEach
    void tearDown() {
        imageRepository.deleteAll();
    }

    @Test
    @DisplayName("/createImage rest api test")
    void save() throws IOException {
        //act
        Image image = imageController.save(prepareImage());
        //assert
        assertNotNull(image);
        assertNotNull(image.getId());
    }

    @Test
    @DisplayName("/createImage rest api test")
    void update() throws IOException {
        //prepare
        Image image = imageRepository.save(prepareImage());
        image.setData(LocalDate.now());
        //act
        imageController.update(image);
        //assert
        assertNotNull(image);
        assertNotNull(image.getId());
        assertNotNull(image.getData());
    }

    @Test
    @DisplayName("/getImageById rest api test")
    void getImageById() throws IOException {
        //prepare
        Image image = imageRepository.save(prepareImage());
        //act
        Image imageById = imageController.getImageById(image.getId());
        //act
        assertNotNull(image);
        assertEquals(image.getId(), imageById.getId());
    }

    @Test
    @DisplayName("/getAllImages rest api test")
    void getAllTheImages() throws IOException {
        //prepare
        imageRepository.save(prepareImage());
        imageRepository.save(prepareImage());
        //act
        ArrayList<Image> images = (ArrayList<Image>) imageController.getAllTheImages();
        //assert
        //assert
        assertNotNull(images);
        assertEquals(2, images.size());
    }

    @Test
    @DisplayName("/delete{id} rest api test")
    void deleteById() throws IOException {
        //prepare
        Image image = imageRepository.save(prepareImage());
        //act
        imageController.deleteById(image.getId());
        Image nullImage = imageRepository.getById(image.getId());
        //assert
        assertNull(nullImage);
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