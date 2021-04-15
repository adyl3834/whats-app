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

    @PostMapping(value = "createStickerPack", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create StickerPack",
            notes = "Save new StickerPack",
            response = StickerPack.class)
    public StickerPack save(@Validated @RequestBody final StickerPack stickerPack) {
        return stickerPackService.save(stickerPack);
    }

    @PutMapping(value = "updateStickerPack", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update StickerPack",
            notes = "Update StickerPack",
            response = StickerPack.class)
    public StickerPack update(@Validated @RequestBody final StickerPack stickerPack) {
        return stickerPackService.save(stickerPack);
    }

    @GetMapping(value = "getAllStickerPacks", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all StickerPacks",
            notes = "Get all StickerPacks ",
            response = StickerPack.class)
    public List<StickerPack> getAllStickerPacks() {
        return stickerPackService.findAll();
    }

    @GetMapping(value = "getAllStickers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all StickerPacks",
            notes = "Get all StickerPacks ",
            response = StickerPack.class)
    public Set<Sticker> getAllStickers(@PathVariable("id") Long id) {
        return stickerPackService.findAllStickers(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete by Id",
            notes = "Delete stickerPack by Id",
            response = StickerPack.class)
    public void delete(@PathVariable("id") Long id) {
        stickerPackService.deleteById(id);
    }
}
