package com.github.as2122.backend.api.controllers.files;

import com.github.as2122.backend.accounts.AccountManager;
import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.SimpleResponse;
import com.github.as2122.backend.files.FileManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.SimpleBeanInfo;

@RestController
public class DeleteFile {

    private final FileManager fileManager;
    private final AccountManagerInterface accountManager;
    private final Gson jsonParser;

    public DeleteFile(FileManager fileManager, AccountManagerInterface accountManager, Gson jsonParser) {
        this.fileManager = fileManager;
        this.accountManager = accountManager;
        this.jsonParser = jsonParser;
    }

    @GetMapping("/delete")
    public String delete(String token, String fileID) {
        final String userID = accountManager.getByName(accountManager.getByToken(token)).getId();
        return fileManager.delete(userID, fileID) ?
                jsonParser.toJson(new SimpleResponse("accepted")) :
                jsonParser.toJson(new SimpleResponse("rejected"));
    }
}
