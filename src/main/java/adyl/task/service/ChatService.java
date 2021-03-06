package adyl.task.service;

import adyl.task.model.Chat;
import adyl.task.model.Massage;
import adyl.task.repository.ChatRepository;
import adyl.task.repository.MassageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MassageRepository massageRepository;

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

    public List<Massage> getMassagesById(Long id) {
        return massageRepository.getMassagesById(id);
    }
}
