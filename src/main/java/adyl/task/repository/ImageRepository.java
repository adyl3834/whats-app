package adyl.task.repository;


import adyl.task.model.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    @Query(value = "select * from Image i where i.id = ?1", nativeQuery = true)
    Image getById(Long id);
}
