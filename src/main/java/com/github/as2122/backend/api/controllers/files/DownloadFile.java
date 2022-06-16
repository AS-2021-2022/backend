package com.github.as2122.backend.api.controllers.files;

import com.github.as2122.backend.accounts.Account;
import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.SimpleResponse;
import com.github.as2122.backend.files.FileManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;

@RestController
public class DownloadFile {

    private Path rootLocation = Path.of("/app/files/");

    private final AccountManagerInterface accountManager;
    private final FileManager fileManager;
    private final Gson jsonParser;

    public DownloadFile(AccountManagerInterface accountManager, FileManager fileManager, Gson jsonParser) {
        this.accountManager = accountManager;
        this.fileManager = fileManager;
        this.jsonParser = jsonParser;
    }

    @GetMapping("/download")
    public Resource loadAsResource(String token, String fileID) throws Exception {
        final String userID = accountManager.getByName(accountManager.getByToken(token)).getId();

        if (!fileManager.canAccessFile(userID, fileID))
            throw new Exception("Access denied");
        try {
            final Path file = rootLocation.resolve(fileID);
            final Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception(
                        "Could not read file: " + fileID);
            }
        } catch (MalformedURLException e) {
            throw new Exception("Could not read file: " + fileID, e);
        }
    }
}
