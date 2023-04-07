package de.ssw.restapp.controllers.stranger;


import de.ssw.restapp.domain.errors.GetFromDatabaseException;
import de.ssw.restapp.domain.errors.SaveToDatabaseException;
import de.ssw.restapp.services.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("api/v1/image")
@AllArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @GetMapping(value = "load/{id}")
    public ResponseEntity<Resource> load(@PathVariable Long id) {
        try {
            final var resource = imageService.getImageData(id);
            return ResponseEntity.ok()
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.valueOf(imageService.getImage(id).getType()))
                    .body(resource);

        } catch (GetFromDatabaseException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("upload")
    public ResponseEntity<String> save(@RequestBody MultipartFile file) {
        try {
            imageService.saveImage(file);
            return ResponseEntity.ok("Image saved successfully");
        } catch (SaveToDatabaseException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Unable to save image");
        }
    }

}
