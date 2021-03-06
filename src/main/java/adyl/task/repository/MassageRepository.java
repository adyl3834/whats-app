package adyl.task.repository;

import adyl.task.model.Massage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MassageRepository extends CrudRepository<Massage, Long> {
    // Получение последние сообщения для чата с шагом в 50 сообщений, для теста я уменьшил на 2 сообщений
    @Query(value = "select * from Massage m where m.chat_id = ?1 ORDER BY m.datetime DESC LIMIT 2", nativeQuery = true)
    List<Massage> getMassagesById(Long id);
}
