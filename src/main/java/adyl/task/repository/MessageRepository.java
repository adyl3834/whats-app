package adyl.task.repository;

import adyl.task.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // Получение последние сообщения для чата с шагом в 50 сообщений, для теста я уменьшил на 2 сообщений
    @Query(value = "select * from Massage m where m.chat_id = ?1 ORDER BY m.datetime DESC LIMIT 2", nativeQuery = true)
    List<Message> findMessagesById(Long id);
}
