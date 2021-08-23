package adyl.task.service;

import adyl.task.model.Sticker;
import adyl.task.repository.StickerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StickerService {
    private StickerRepository stickerRepository;

    public StickerService(StickerRepository stickerRepository) {
        this.stickerRepository = stickerRepository;
    }

    public Sticker save(Sticker sticker) {
        return stickerRepository.save(sticker);
    }

    public List<Sticker> findAll() {
        return stickerRepository.findAll();
    }

    public void deleteById(Long id) {
        stickerRepository.deleteById(id);
    }
}
