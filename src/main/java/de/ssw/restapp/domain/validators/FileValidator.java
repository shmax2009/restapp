package de.ssw.restapp.domain.validators;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileValidator implements Validator<MultipartFile> {

    @Value("${application.storage.max-file-size}")
    public Long MAX_FILE_SIZE;


    @Override
    public String validate(MultipartFile file) {
        if (file == null)
            return "File is null";

        if (file.isEmpty())
            return "File is empty";

        if (file.getSize() > MAX_FILE_SIZE)
            return "File is too large";

        return null;
    }
}
