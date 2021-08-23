package adyl.task.repository;

import adyl.task.model.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long> {
    @Query(value = "select * from Sticker s where s.sticker_pack_id = ?1", nativeQuery = true)
    Set<Sticker> findAllStickers(Long id);
}
