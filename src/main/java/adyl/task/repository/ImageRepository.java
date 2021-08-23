package adyl.task.repository;


import adyl.task.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findImageById(Long id);
}
