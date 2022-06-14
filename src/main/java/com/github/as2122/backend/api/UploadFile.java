package com.github.as2122.backend.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

@RestController
public class UploadFile {

    private Path rootLocation = Path.of("/app/files/");

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file/*, String name*/) {
        final String uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("./bababowie.txt")
                .toUriString();
        if (file == null)
            return "File not found";

        final Path targetLocation = Path.of(uri);

        try {
            store(file);
            if (Files.exists(targetLocation))
                return "IT WORKED!";
            return "File not saved";
        } catch (Exception e) {
            return Arrays.toString(e.getStackTrace())
                    .replaceAll(",", "\n")
                    .replaceAll("([|])", "");
        }
    }

    private void store(MultipartFile file) throws Exception {
        if (!(Files.exists(rootLocation) && Files.isDirectory(rootLocation))) {
            Files.createDirectory(rootLocation);
            System.out.println("Created folder");
        }

        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            System.out.println("Saving to " + destinationFile);
//            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
//                // This is a security check
//                throw new Exception(
//                        "Cannot store file outside current directory.");
//            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new Exception("Failed to store file.", e);
        }

    }
}
