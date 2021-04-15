package adyl.task.repository;

import adyl.task.model.Sticker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StickerRepository extends CrudRepository<Sticker, Long> {
    @Query(value = "select * from Sticker s where s.sticker_pack_id = ?1", nativeQuery = true)
    Set<Sticker> findAllStickers(Long id);
}
