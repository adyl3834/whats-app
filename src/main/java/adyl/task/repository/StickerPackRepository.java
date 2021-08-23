package adyl.task.repository;

import adyl.task.model.StickerPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StickerPackRepository extends JpaRepository<StickerPack, Long> {
}
