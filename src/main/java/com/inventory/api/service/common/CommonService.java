package com.inventory.api.service.common;

import com.inventory.api.data.repository.PenggunaRepository;
import com.inventory.api.data.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    protected TokenRepository tokenRepository;

    @Autowired
    protected PenggunaRepository penggunaRepository;
}
