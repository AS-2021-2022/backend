package com.github.as2122.backend.api.controllers.files;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.GetFilesListResponse;
import com.github.as2122.backend.files.FileManager;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetFilesList {
    private final Gson jsonParser;
    private final AccountManagerInterface accountManager;
    private final FileManager fileManager;


    public GetFilesList(Gson jsonParser, AccountManagerInterface accountManager, FileManager fileManager) {
        this.jsonParser = jsonParser;
        this.accountManager = accountManager;
        this.fileManager = fileManager;
    }

    @GetMapping("/getFilesList")
    public String getFilesList(String token) {
        final String userID = accountManager.getByName(accountManager.getByToken(token)).getId();
        return jsonParser.toJson(new GetFilesListResponse("accepted",
                fileManager.getAllUserFiles(userID)
        ));
    }
}
