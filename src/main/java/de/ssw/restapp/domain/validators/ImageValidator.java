package de.ssw.restapp.domain.validators;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


@Service
@AllArgsConstructor
public class ImageValidator implements Validator<MultipartFile> {

    private final FileValidator fileValidator;

    @Override
    public String validate(MultipartFile object) {
        String fileValidation = fileValidator.validate(object);
        if (fileValidation != null)
            return fileValidation;

        if (Objects.isNull(object.getContentType())
                || object.getContentType().isEmpty())
            return "File has no content type";

        if (!object.getContentType().startsWith("image/")) {
            return "File is not an image";
        }

        if (object.getContentType().equals("image/gif")
                || object.getContentType().equals("image/svg+xml"))
            return "File is not supported";

        return null;
    }
}
