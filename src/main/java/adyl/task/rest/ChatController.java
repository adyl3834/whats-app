package adyl.task.rest;

import adyl.task.model.Chat;
import adyl.task.model.Image;
import adyl.task.model.Message;
import adyl.task.service.ChatService;
import adyl.task.service.ImageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("whatsapp/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private ImageService imageService;

    @PostMapping(value = "createChat", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new chat",
            notes = "Save new Account",
            response = Chat.class)
    public Chat save(@Validated @RequestBody final Chat chat) {
        Image image = chat.getChatPicture();
        if (image != null && image.getId() == null) {
            image = imageService.save(image);
            chat.setChatPicture(image);
        }
        return chatService.save(chat);
    }

    @PutMapping(value = "updateChat", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update chat",
            notes = "Update chat",
            response = Chat.class)
    public Chat update(@Validated @RequestBody final Chat chat) {
        return chatService.update(chat);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get by Id",
            notes = "Get Chat by Id",
            response = Chat.class)
    public Chat getChatById(@PathVariable("id") Long id) {
        return chatService.getByChatId(id);
    }

    @GetMapping(value = "/getMassagesById", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get by Id",
            notes = "Get Massage by Id",
            response = Chat.class)
    public List<Message> getMessagesById(@PathVariable("id") Long id) {
        return chatService.getMessagesById(id);
    }

    @GetMapping(value = "getAllChats", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Chats",
            notes = "Get all chats",
            response = Chat.class)
    public List<Chat> getAllTheAccounts() {
        return chatService.findAll();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete by Id",
            notes = "Delete Chat by Id",
            response = Chat.class)
    public void deleteById(@PathVariable("id") Long id) {
        chatService.deleteById(id);
    }
}
