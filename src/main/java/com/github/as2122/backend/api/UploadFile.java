package com.github.as2122.backend.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
public class UploadFile {

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file, String name) {
        final String uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(name)
                .toUriString();

        final Path targetLocation = Path.of(uri);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            if (Files.exists(targetLocation))
                return "IT WORKED!";
            return "File not saved";
        } catch (IOException e) {
            return "ERROR";
        }
    }
}
