package adyl.task.service;

import adyl.task.model.Sticker;
import adyl.task.model.StickerPack;
import adyl.task.repository.StickerPackRepository;
import adyl.task.repository.StickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StickerPackService {
    @Autowired
    private StickerPackRepository stickerPackRepository;
    @Autowired
    private StickerRepository stickerRepository;

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
