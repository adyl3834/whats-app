package adyl.task.rest;

import adyl.task.model.Sticker;
import adyl.task.model.StickerPack;
import adyl.task.service.StickerPackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("whatsapp/pack")
public class StickerPackController {
    @Autowired
    private StickerPackService stickerPackService;

    @PostMapping(value = "saveStickerPack", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save new stickerPack",
            notes = "Save new stickerPack",
            response = StickerPack.class)
    public StickerPack save(@Validated @RequestBody final StickerPack stickerPack) {
        return stickerPackService.save(stickerPack);
    }

    @PutMapping(value = "updateStickerPack", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update stickerPack",
            notes = "Update stickerPack",
            response = StickerPack.class)
    public StickerPack update(@Validated @RequestBody final StickerPack stickerPack) {
        return stickerPackService.save(stickerPack);
    }

    @GetMapping(value = "getAllStickerPacks", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all stickerPacks",
            notes = "Get all stickerPacks ",
            response = StickerPack.class)
    public List<StickerPack> getAllStickerPacks() {
        return stickerPackService.findAll();
    }

    @GetMapping(value = "getAllStickers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all stickerPacks",
            notes = "Get all stickerPacks ",
            response = StickerPack.class)
    public Set<Sticker> getAllStickers(@PathVariable("id") Long id) {
        return stickerPackService.findAllStickers(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete stickerPack by Id",
            notes = "Delete stickerPack by Id",
            response = StickerPack.class)
    public void delete(@PathVariable("id") Long id) {
        stickerPackService.deleteById(id);
    }
}
