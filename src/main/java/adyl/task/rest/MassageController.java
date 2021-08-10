package adyl.task.rest;

import adyl.task.model.Massage;
import adyl.task.service.MassageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("whatsapp/massage")
public class MassageController {
    @Autowired
    private MassageService massageService;

    @PostMapping(value = "createMessage", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save new message",
            notes = "Save new message",
            response = Massage.class)
    public Massage save(@Validated @RequestBody final Massage massage) {
        return massageService.save(massage);
    }

    @PutMapping(value = "updateMessage", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Message",
            notes = "Update Message",
            response = Massage.class)
    public Massage update(@Validated @RequestBody final Massage massage) {
        return massageService.save(massage);
    }

    @GetMapping(value = "getAllMessages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all messages",
            notes = "Get all messages ",
            response = Massage.class)
    public List<Massage> getAllMassages() {
        return massageService.findAll();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete message by Id",
            notes = "Delete message by Id",
            response = Massage.class)
    public void delete(@PathVariable("id") Long id) {
        massageService.deleteById(id);
    }
}
