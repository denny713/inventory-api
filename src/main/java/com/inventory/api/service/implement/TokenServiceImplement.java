package com.inventory.api.service.implement;

import com.inventory.api.data.entity.core.Token;
import com.inventory.api.service.common.CommonService;
import com.inventory.api.service.content.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImplement extends CommonService implements TokenService {

    @Override
    public Token getByClient(String client) {
        return tokenRepository.findByClient(client);
    }
}
