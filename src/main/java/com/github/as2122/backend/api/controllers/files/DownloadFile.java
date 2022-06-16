package com.github.as2122.backend.api.controllers.files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;

@RestController
public class DownloadFile {

    private Path rootLocation = Path.of("/app/files/");

    @GetMapping("/download")
    public Resource loadAsResource(String filename) throws Exception {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new Exception("Could not read file: " + filename, e);
        }
    }
}
