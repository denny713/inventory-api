package com.inventory.api.service.implement;

import com.inventory.api.data.entity.core.Pengguna;
import com.inventory.api.service.common.CommonService;
import com.inventory.api.service.content.AccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplement extends CommonService implements AccountService, UserDetailsService {

    @Override
    public Pengguna getByUserId(Long userId) {
        return penggunaRepository.findByUserId(userId);
    }

    @Override
    public Pengguna getByUsername(String username) {
        return penggunaRepository.findByUsername(username);
    }

    @Override
    public Pengguna simpanPengguna(Pengguna pengguna) {
        return penggunaRepository.save(pengguna);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return penggunaRepository.findByUsername(s);
    }
}
