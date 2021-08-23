package adyl.task.service;

import adyl.task.model.Sticker;
import adyl.task.model.StickerPack;
import adyl.task.repository.StickerPackRepository;
import adyl.task.repository.StickerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StickerPackService {
    private StickerPackRepository stickerPackRepository;
    private StickerRepository stickerRepository;

    public StickerPackService(StickerPackRepository stickerPackRepository, StickerRepository stickerRepository) {
        this.stickerPackRepository = stickerPackRepository;
        this.stickerRepository = stickerRepository;
    }

    public StickerPack save(StickerPack stickerPack) {
        return stickerPackRepository.save(stickerPack);
    }

    public List<StickerPack> findAll() {
        return (List<StickerPack>) stickerPackRepository.findAll();
    }

    public void deleteById(Long id) {
        stickerPackRepository.deleteById(id);
    }

    public Set<Sticker> findAllStickers(Long id) {
        return stickerRepository.findAllStickers(id);
    }
}
