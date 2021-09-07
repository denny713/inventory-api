package com.inventory.api.service.content;

import com.inventory.api.data.entity.core.Token;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TokenService {

    public Token getByClient(String client);
}
