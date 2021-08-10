package adyl.task.rest;

import adyl.task.model.Image;
import adyl.task.service.ImageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/whatsapp/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping(value = "saveImage", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save new chat ",
            notes = "Save new image",
            response = Image.class)
    public Image save(@Validated @RequestBody final Image image) {
        return imageService.save(image);
    }

    @PutMapping(value = "updateImage", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update image",
            notes = "Update image",
            response = Image.class)
    public Image update(@Validated @RequestBody final Image image) {
        return imageService.update(image);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get image by Id",
            notes = "Get image by Id",
            response = Image.class)
    public Image getImageById(@PathVariable("id") Long id) {
        return imageService.getByChatId(id);
    }

    @GetMapping(value = "getAllImages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all images",
            notes = "Get all images",
            response = Image.class)
    public List<Image> getAllTheImages() {
        return imageService.findAll();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete image by Id",
            notes = "Delete image by Id",
            response = Image.class)
    public void deleteById(@PathVariable("id") Long id) {
        imageService.deleteById(id);
    }

}
