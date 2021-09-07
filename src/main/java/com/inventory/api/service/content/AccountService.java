package com.inventory.api.service.content;

import com.inventory.api.data.entity.core.Pengguna;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountService {

    public Pengguna getByUsername(String username);

    public void simpanPengguna(Pengguna pengguna);
}
