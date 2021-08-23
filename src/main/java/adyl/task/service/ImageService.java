package adyl.task.service;

import adyl.task.model.Image;
import adyl.task.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public Image update(Image chat) {
        return imageRepository.save(chat);
    }

    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }

    public Image getByChatId(Long id) {
        return imageRepository.getById(id);
    }

    public List<Image> findAll() {
        return (List<Image>) imageRepository.findAll();
    }
}
