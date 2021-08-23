package adyl.task.service;

import adyl.task.exception.MessageTypeException;
import adyl.task.model.Message;
import adyl.task.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static adyl.task.type.MessageType.MASSAGE;
import static adyl.task.type.MessageType.STICKER;

@Service
public class MassageService {
    private MessageRepository messageRepository;

    public MassageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message) {
        if (message.getMessageType().equals(MASSAGE) && message.getSticker_id() != null && message.getMassage() == null) {
            throw new MessageTypeException("MassageType is MASSAGE, Massage must be text!");
        } else if (message.getMessageType().equals(STICKER) && message.getMassage() != null && message.getSticker_id() == null) {
            throw new MessageTypeException("MassageType is STICKER, Massage must be sticker!");
        }
        return messageRepository.save(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }
}
