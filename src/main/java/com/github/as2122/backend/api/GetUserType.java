package com.github.as2122.backend.api;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.GetUserTypeResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserType {

    @Autowired
    private final Gson jsonSerializer;
    private final AccountManagerInterface accountManager;

    public GetUserType(Gson jsonSerializer, AccountManagerInterface accountManager) {
        this.jsonSerializer = jsonSerializer;
        this.accountManager = accountManager;
    }

    @GetMapping("/getUserType")
    public String getUserType(String token) {
        return jsonSerializer.toJson(new GetUserTypeResponse("accepted", accountManager.getByName(accountManager.getByToken(token)).getLevel().name()));
    }
}
