package adyl.task.service;

import adyl.task.exception.MassageTypeException;
import adyl.task.model.Massage;
import adyl.task.repository.MassageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static adyl.task.type.MassageType.MASSAGE;
import static adyl.task.type.MassageType.STICKER;

@Service
public class MassageService {
    @Autowired
    private MassageRepository massageRepository;

    public Massage save(Massage massage) {
        if (massage.getMassageType().equals(MASSAGE) && massage.getSticker_id() != null && massage.getMassage() == null) {
            throw new MassageTypeException("MassageType is MASSAGE, Massage must be text!");
        } else if (massage.getMassageType().equals(STICKER) && massage.getMassage() != null && massage.getSticker_id() == null) {
            throw new MassageTypeException("MassageType is STICKER, Massage must be sticker!");
        }
        return massageRepository.save(massage);
    }

    public List<Massage> findAll() {
        return (List<Massage>) massageRepository.findAll();
    }

    public void deleteById(Long id) {
        massageRepository.deleteById(id);
    }
}
