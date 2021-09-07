package com.inventory.api.data.model.request;

import lombok.Data;

@Data
public class LoginRequest {

    String username;
    String password;
}
