package com.inventory.api.controller.core;

import com.inventory.api.controller.base.BaseController;
import com.inventory.api.data.entity.core.Pengguna;
import com.inventory.api.data.entity.core.Token;
import com.inventory.api.data.model.data.CheckResultData;
import com.inventory.api.data.model.data.UserLoginData;
import com.inventory.api.data.model.request.LoginRequest;
import com.inventory.api.data.model.response.Response;
import com.inventory.api.enumeration.PesanEnum;
import com.inventory.api.util.EncryptUtil;
import com.inventory.api.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@RequestMapping("/api/account")
public class AccountController extends BaseController {

    @PostMapping("/logout")
    @ResponseBody
    public Response doLogout() {
        return ResponseUtil.setSuccess(successStatus, PesanEnum.SUKSES_LOGOUT.getMessage(), null);
    }

    @PostMapping("/login")
    @ResponseBody
    public Response doLogin(@Valid @RequestBody LoginRequest login) {
        try {
            CheckResultData cek = loginCheck(login);
            if (Boolean.FALSE.equals(cek.getResult())) {
                response = ResponseUtil.setFailed(errorStatus, PesanEnum.GAGAL_LOGIN.getMessage() + " : " + cek.getMessage());
            } else {
                UserLoginData loginData = doSetUserLogin(login);
                response = ResponseUtil.setSuccess(successStatus, PesanEnum.SUKSES_LOGIN.getMessage(), loginData);
            }
        } catch (Exception e) {
            response = ResponseUtil.setFailed(errorStatus, PesanEnum.GAGAL_LOGIN.getMessage() + " : " + e.getMessage());
        }
        return response;
    }

    public CheckResultData loginCheck(LoginRequest login) throws NoSuchAlgorithmException {
        boolean hasil = false;
        String pesan = "";
        Pengguna user = accountService.getByUsername(login.getUsername());
        if (user != null) {
            if (!user.getPassword().equals(EncryptUtil.encrypt(login.getPassword()))) {
                pesan = PesanEnum.TIDAK_SESUAI.formatPesan("Username Atau Password").getMessage();
                user.setLoginGagal(user.getLoginGagal() + 1);
                accountService.simpanPengguna(user);
            } else if (user.getLoginGagal() >= 3) {
                pesan = PesanEnum.AKUN_TERKUNCI.getMessage();
            } else {
                hasil = true;
            }
        } else {
            pesan = PesanEnum.DATA_TIDAK_DITEMUKAN.formatPesan("User " + login.getUsername()).getMessage();
        }
        return new CheckResultData(hasil, pesan);
    }

    public UserLoginData doSetUserLogin(LoginRequest login) throws HttpRequestMethodNotSupportedException {
        Token token = tokenService.getByClient(getClientName());
        if (token != null) {
            Map<String, String> param = new HashMap<>();
            param.put("grant_type", "password");
            param.put("username", login.getUsername());
            param.put("password", login.getPassword());
            List<String> grants = Arrays.asList(token.getAuthority().split(","));
            Set<GrantedAuthority> auth = new HashSet<>();
            grants.forEach(x -> auth.add(new SimpleGrantedAuthority(x)));
            User principal = new User(getClientName(), getClientPass(), true, true, true, true, auth);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principal, getClientPass(), auth);
            ResponseEntity<OAuth2AccessToken> tokenResponse = tokenEndpoint.postAccessToken(authToken, param);
            Pengguna user = accountService.getByUsername(login.getUsername());
            if (tokenResponse != null && user != null) {
                OAuth2AccessToken oauth = tokenResponse.getBody();
                if (oauth != null) {
                    StringBuilder scopes = new StringBuilder();
                    Objects.requireNonNull(tokenResponse.getBody()).getScope().forEach(str -> scopes.append(str).append(" "));
                    String scope = scopes.substring(0, scopes.toString().length() - 1);
                    return new UserLoginData(user.getUserId(), oauth.getValue(), oauth.getRefreshToken().getValue(), oauth.getTokenType(), scope, oauth.getAdditionalInformation().get("jti").toString(), oauth.isExpired(), oauth.getExpiration(), oauth.getExpiresIn());
                }
            }
        }
        return new UserLoginData();
    }
}
