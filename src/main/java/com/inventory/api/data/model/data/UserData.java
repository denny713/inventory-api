package com.inventory.api.data.model.data;

import lombok.Data;

@Data
public class UserData {

    public UserData() {
        super();
    }

    public UserData(Long userId, String username, String nama) {
        this.userId = userId;
        this.username = username;
        this.nama = nama;
    }

    private Long userId;
    private String username;
    private String nama;
}
