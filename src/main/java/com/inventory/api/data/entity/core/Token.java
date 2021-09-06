package com.inventory.api.data.entity.core;

import com.inventory.api.data.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "token")
public class Token extends BaseEntity implements ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Long idToken;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "client")
    private String client;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "grant_types")
    private String grantTypes;

    @Column(name = "access_valid")
    private Long accessValid;

    @Column(name = "refresh_valid")
    private Long refreshValid;

    @Column(name = "url")
    private String url;

    @Column(name = "scopes")
    private String scopes;

    @Column(name = "secret")
    private String secret;

    @Column(name = "is_scope")
    private Boolean isScope;

    @Column(name = "is_secret")
    private Boolean isSecret;

    @Column(name = "resources")
    private String resources;

    @Override
    public String getClientId() {
        return client;
    }

    @Override
    public Set<String> getResourceIds() {
        Set<String> res = new HashSet<>();
        res.add(resources);
        return res;
    }

    @Override
    public boolean isSecretRequired() {
        return isSecret;
    }

    @Override
    public String getClientSecret() {
        return secret;
    }

    @Override
    public boolean isScoped() {
        return isScope;
    }

    @Override
    public Set<String> getScope() {
        return new HashSet<>(Arrays.asList(scopes.split(",")));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return new HashSet<>(Arrays.asList(grantTypes.split(",")));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        Set<String> uris = new HashSet<>();
        if (url != null) {
            return new HashSet<>(Arrays.asList(url.split(",")));
        }
        return uris;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grants = new HashSet<>();
        String[] strs = authorities.split(",");
        for (String atr : strs) {
            grants.add(new SimpleGrantedAuthority(atr));
        }
        return grants;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessValid.intValue();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshValid.intValue();
    }

    @Override
    public boolean isAutoApprove(String s) {
        return approved;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
