package adyl.task.rest;

import adyl.task.model.Message;
import adyl.task.service.MassageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("whatsapp/massage")
public class MessageController {
    @Autowired
    private MassageService massageService;

    @PostMapping(value = "createMassage", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create massage",
            notes = "Save new massage",
            response = Message.class)
    public Message save(@Validated @RequestBody final Message message) {
        return massageService.save(message);
    }

    @PutMapping(value = "updateMassage", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Massage",
            notes = "Update Massage",
            response = Message.class)
    public Message update(@Validated @RequestBody final Message message) {
        return massageService.save(message);
    }

    @GetMapping(value = "getAllMassages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Massages",
            notes = "Get all Massages ",
            response = Message.class)
    public List<Message> getAllMassages() {
        return massageService.findAll();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete by Id",
            notes = "Delete massage by Id",
            response = Message.class)
    public void delete(@PathVariable("id") Long id) {
        massageService.deleteById(id);
    }
}
