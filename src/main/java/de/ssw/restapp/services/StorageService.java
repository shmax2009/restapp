package de.ssw.restapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@Slf4j
public class StorageService {

    @Value("${application.storage.location}")
    private String LOCATION;

    public static final String IMAGE_DIRECTORY_NAME = "images/";

    public void saveFile(String fileName, MultipartFile file) throws IOException {
        log.info("Saving file: " + fileName);
        File savedFile = new File(LOCATION + fileName);
        if (!savedFile.exists()) {
            savedFile.getParentFile().mkdirs();
            savedFile.createNewFile();
        }

        file.transferTo(new File(LOCATION + fileName));
    }


    public void saveImage(String fileName, MultipartFile file) throws IOException {
        saveFile(IMAGE_DIRECTORY_NAME + fileName, file);
    }

    public ByteArrayResource getFileData(String fileName) throws IOException {
        File file = new File(LOCATION + fileName);
        return new ByteArrayResource(Files.readAllBytes(file.toPath()));
    }

    public ByteArrayResource getImageData(String fileName) throws IOException {
        return getFileData(IMAGE_DIRECTORY_NAME + fileName);
    }

    public void deleteFile(String fileName) {
        File file = new File(LOCATION + fileName);
        if (file.exists())
            file.delete();
    }

    public void deleteImage(String fileName) throws IOException {
        deleteFile(IMAGE_DIRECTORY_NAME + fileName);
    }

}
