package adyl.task.service;

import adyl.task.model.Chat;
import adyl.task.model.Message;
import adyl.task.repository.ChatRepository;
import adyl.task.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private ChatRepository chatRepository;
    private MessageRepository messageRepository;

    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }

    public Chat update(Chat chat) {
        return chatRepository.save(chat);
    }

    public void deleteById(Long id) {
        chatRepository.deleteById(id);
    }

    public Chat getByChatId(Long id) {
        return chatRepository.getById(id);
    }

    public List<Chat> findAll() {
        return (List<Chat>) chatRepository.findAll();
    }

    public List<Message> getMessagesById(Long id) {
        return messageRepository.getMessagesById(id);
    }
}
