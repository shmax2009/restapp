package de.ssw.restapp.services;

import de.ssw.restapp.domain.errors.DeleteFromDatabaseException;
import de.ssw.restapp.domain.errors.GetFromDatabaseException;
import de.ssw.restapp.domain.errors.SaveToDatabaseException;
import de.ssw.restapp.domain.model.Image;
import de.ssw.restapp.domain.repository.ImageRepository;
import de.ssw.restapp.domain.validators.ImageValidator;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private final StorageService storageService;

    private final ImageValidator imageValidator;

    private void deleteImage(Long id) throws DeleteFromDatabaseException {
        try {
            storageService.deleteImage(getImage(id).getName());
            imageRepository.deleteById(id);
        } catch (IOException e) {
            throw new DeleteFromDatabaseException("Image", e.getMessage());
        }
    }

    public void saveImage(MultipartFile file) throws SaveToDatabaseException {
        String report = imageValidator.validate(file);
        if (report != null)
            throw new SaveToDatabaseException("Image", report);
        try {
            Image image = Image.builder()
                    .type(file.getContentType())
                    .build();

            imageRepository.save(image);
            storageService.saveImage(image.getName(), file);
        } catch (Exception e) {
            throw new SaveToDatabaseException("Image", e.getMessage());
        }
    }

    public Image getImage(Long id) throws GetFromDatabaseException {
        return imageRepository.findById(id).orElseThrow(() -> new GetFromDatabaseException("Image", "Image not found"));
    }

    public ByteArrayResource getImageData(Long id) throws GetFromDatabaseException {
        try {
            return storageService.getImageData(getImage(id).getName());
        } catch (IOException e) {
            throw new GetFromDatabaseException("Image", e.getMessage());
        }
    }
}
