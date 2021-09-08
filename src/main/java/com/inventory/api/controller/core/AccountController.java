package com.inventory.api.controller.core;

import com.inventory.api.controller.base.BaseController;
import com.inventory.api.data.entity.core.Pengguna;
import com.inventory.api.data.entity.core.Token;
import com.inventory.api.data.model.data.*;
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
                return ResponseUtil.setFailed(errorStatus, PesanEnum.GAGAL_LOGIN.getMessage() + " : " + cek.getMessage());
            }
            UserLoginData loginData = doSetUserLogin(login);
            if (loginData.getUser() == null) {
                return ResponseUtil.setFailed(errorStatus, PesanEnum.GAGAL_LOGIN.getMessage() + " : " + PesanEnum.DATA_TIDAK_DITEMUKAN.formatPesan("User").getMessage());
            }
            return ResponseUtil.setSuccess(successStatus, PesanEnum.SUKSES_LOGIN.getMessage(), loginData);
        } catch (Exception e) {
            return ResponseUtil.setFailed(errorStatus, PesanEnum.GAGAL_LOGIN.getMessage() + " : " + e.getMessage());
        }
    }

    public CheckResultData loginCheck(LoginRequest login) throws NoSuchAlgorithmException {
        boolean hasil = false;
        String pesan = "";
        Pengguna user = accountService.getByUsername(login.getUsername());
        if (user != null) {
            if (user.getLoginGagal() >= 3) {
                pesan = PesanEnum.AKUN_TERKUNCI.getMessage();
            } else {
                if (!user.getPassword().equals(EncryptUtil.encrypt(login.getPassword()))) {
                    pesan = PesanEnum.TIDAK_SESUAI.formatPesan("Username Atau Password").getMessage();
                    user.setLoginGagal(user.getLoginGagal() + 1);
                    accountService.simpanPengguna(user);
                } else {
                    hasil = true;
                }
            }
        } else {
            pesan = PesanEnum.DATA_TIDAK_DITEMUKAN.formatPesan("User " + login.getUsername()).getMessage();
        }
        return new CheckResultData(hasil, pesan);
    }

    public UserLoginData doSetUserLogin(LoginRequest login) throws HttpRequestMethodNotSupportedException {
        Pengguna user = accountService.getByUsername(login.getUsername());
        if (user != null && user.getJabatan() != null) {
            Token token = user.getJabatan().getToken();
            if (token != null) {
                Map<String, String> param = new HashMap<>();
                param.put("grant_type", "password");
                param.put("username", login.getUsername());
                param.put("password", login.getPassword());
                List<String> grants = Arrays.asList(token.getAuthority().split(","));
                Set<GrantedAuthority> auth = new HashSet<>();
                grants.forEach(x -> auth.add(new SimpleGrantedAuthority(x)));
                User principal = new User(token.getClient(), token.getSecret(), true, true, true, true, auth);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principal, token.getSecret(), auth);
                ResponseEntity<OAuth2AccessToken> tokenResponse = tokenEndpoint.postAccessToken(authToken, param);
                if (tokenResponse != null) {
                    OAuth2AccessToken oauth = tokenResponse.getBody();
                    if (oauth != null) {
                        StringBuilder scopes = new StringBuilder();
                        Objects.requireNonNull(tokenResponse.getBody()).getScope().forEach(str -> scopes.append(str).append(" "));
                        String scope = scopes.substring(0, scopes.toString().length() - 1);
                        UserData userData = new UserData(user.getUserId(), user.getUsername(), user.getDeskripsi());
                        TokenData tokenData = new TokenData(oauth.getValue(), oauth.getRefreshToken().getValue(), oauth.getTokenType(), scope, oauth.getAdditionalInformation().get("jti").toString(), oauth.isExpired(), oauth.getExpiration(), oauth.getExpiresIn());
                        List<AksesData> aksesData = new ArrayList<>();
                        user.getJabatan().getAkses().forEach(x -> aksesData.add(new AksesData(x.getId().getIdMenu(), x.getMenu().getParentMenu(), x.getMenu().getDeskripsi(), x.getReadOnly())));
                        return new UserLoginData(userData, tokenData, aksesData);
                    }
                }
            }
        }
        return new UserLoginData();
    }
}
