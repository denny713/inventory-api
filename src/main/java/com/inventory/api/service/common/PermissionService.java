package com.inventory.api.service.common;

import com.inventory.api.data.entity.core.Akses;
import com.inventory.api.data.entity.core.Pengguna;
import com.inventory.api.data.entity.embed.AksesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service(value = "api")
public class PermissionService {

    @Autowired
    private Environment env;

    public Boolean cek(Long permit) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Pengguna user = (Pengguna) auth.getPrincipal();
        if (user.getUserId() == Long.parseLong(Objects.requireNonNull(env.getProperty("auth.user-admin")))) {
            return true;
        }
        AksesId aksesId = new AksesId(user.getIdJabatan(), permit);
        Akses akses = new Akses();
        akses.setId(aksesId);
        return user.getJabatan().getAkses().contains(akses);
    }
}
