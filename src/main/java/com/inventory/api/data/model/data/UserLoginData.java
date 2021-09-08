package com.inventory.api.data.model.data;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginData {

    public UserLoginData() {
        super();
    }

    public UserLoginData(UserData user, TokenData token, List<AksesData> akses) {
        this.user = user;
        this.token = token;
        this.akses = akses;
    }

    private UserData user;
    private TokenData token;
    private List<AksesData> akses;
}
