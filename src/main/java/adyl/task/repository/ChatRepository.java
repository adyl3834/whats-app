package adyl.task.repository;

import adyl.task.model.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    @Query(value = "select * from Chat c where c.id = ?1", nativeQuery = true)
    Chat getById(Long id);
}
