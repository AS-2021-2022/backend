package com.github.as2122.backend.api.controllers.files;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.SimpleResponse;
import com.github.as2122.backend.api.responses.UploadFileResponse;
import com.github.as2122.backend.files.FileManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.beans.SimpleBeanInfo;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

@RestController
public class UploadFile {

    private final Gson jsonParser;
    private final AccountManagerInterface accountManager;
    @Autowired
    private FileManager fileManager;

    private Path rootLocation = Path.of("/app/files/");

    public UploadFile(Gson jsonParser, AccountManagerInterface accountManager) {
        this.jsonParser = jsonParser;
        this.accountManager = accountManager;
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file, String token) {
        if (file == null)
            return "File not found";

        final String userID = accountManager.getByName(accountManager.getByToken(token)).getId();
        try {
            final String fileID = fileManager.storeFile(file, userID);
            if (Files.exists(this.rootLocation.resolve(
                            Paths.get(fileID))
                    .normalize().toAbsolutePath()))
                return jsonParser.toJson(new SimpleResponse("accepted"));
            return jsonParser.toJson(new SimpleResponse("rejected"));
        } catch (Exception e) {
            return jsonParser.toJson(new SimpleResponse("rejected"));
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
