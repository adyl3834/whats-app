package adyl.task.rest;

import adyl.task.model.Sticker;
import adyl.task.service.StickerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("whatsapp/sticker")
public class StickerController {
    @Autowired
    private StickerService stickerService;

    @PostMapping(value = "saveSticker", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save new sticker",
            notes = "Save new sticker",
            response = Sticker.class)
    public Sticker save(@Validated @RequestBody final Sticker sticker) {
        return stickerService.save(sticker);
    }

    @PutMapping(value = "updateSticker", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update sticker",
            notes = "Update sticker",
            response = Sticker.class)
    public Sticker update(@Validated @RequestBody final Sticker sticker) {
        return stickerService.save(sticker);
    }

    @GetMapping(value = "getAllStickers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all stickers",
            notes = "Get all stickers ",
            response = Sticker.class)
    public List<Sticker> getAllStickers() {
        return stickerService.findAll();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete sticker by Id",
            notes = "Delete sticker by Id",
            response = Sticker.class)
    public void delete(@PathVariable("id") Long id) {
        stickerService.deleteById(id);
    }
}
