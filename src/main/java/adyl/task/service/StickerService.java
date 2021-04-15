package adyl.task.service;

import adyl.task.model.Sticker;
import adyl.task.repository.StickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StickerService {
    @Autowired
    private StickerRepository stickerRepository;

    public Sticker save(Sticker sticker) {
        return stickerRepository.save(sticker);
    }

    public List<Sticker> findAll() {
        return (List<Sticker>) stickerRepository.findAll();
    }

    public void deleteById(Long id) {
        stickerRepository.deleteById(id);
    }
}
