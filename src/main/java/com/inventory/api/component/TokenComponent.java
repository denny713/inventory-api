package com.inventory.api.component;

import com.inventory.api.data.entity.core.Pengguna;
import com.inventory.api.service.content.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class TokenComponent extends DefaultAccessTokenConverter {

    @Autowired
    private AccountService accountService;

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        final OAuth2Authentication auth = super.extractAuthentication(map);
        final Pengguna user = accountService.getByUsername((String) auth.getPrincipal());
        return new OAuth2Authentication(auth.getOAuth2Request(), auth.getUserAuthentication()) {
            @Override
            public Collection<GrantedAuthority> getAuthorities() {
                if (user != null) {
                    return (Collection<GrantedAuthority>) user.getAuthorities();
                }
                return auth.getAuthorities();
            }
        };
    }
}
