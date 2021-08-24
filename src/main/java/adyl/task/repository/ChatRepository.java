package adyl.task.repository;

import adyl.task.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findChatById(Long id);
}
