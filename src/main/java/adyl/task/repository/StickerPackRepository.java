package adyl.task.repository;

import adyl.task.model.StickerPack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StickerPackRepository extends CrudRepository<StickerPack, Long> {
}
