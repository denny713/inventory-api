package com.inventory.api.data.model.data;

import lombok.Data;

import java.util.Date;

@Data
public class TokenData {

    public TokenData() {
        super();
    }

    public TokenData(String accessToken, String refreshToken, String tokenType, String scope, String jti, Boolean isExpired, Date expiration, Integer expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.scope = scope;
        this.jti = jti;
        this.isExpired = isExpired;
        this.expiration = expiration;
        this.expiresIn = expiresIn;
    }

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String scope;
    private String jti;
    private Boolean isExpired;
    private Date expiration;
    private Integer expiresIn;
}
