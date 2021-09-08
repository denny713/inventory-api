package com.inventory.api.controller.base;

import com.inventory.api.data.model.response.Response;
import com.inventory.api.service.content.AccountService;
import com.inventory.api.service.content.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    protected TokenEndpoint tokenEndpoint;

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected TokenService tokenService;

    protected int errorStatus = 500;
    protected int successStatus = 200;
}
