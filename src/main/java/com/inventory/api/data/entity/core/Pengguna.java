package com.inventory.api.data.entity.core;

import com.inventory.api.constant.StatusConstant;
import com.inventory.api.data.entity.base.MasterEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pengguna", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class Pengguna extends MasterEntity implements UserDetails {

    @Id
    @Column(name = "id_user")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "kode_cabang")
    private String kodeCabang;

    @Column(name = "kode_jabatan")
    private Long kodeJabatan;

    @Column(name = "login_gagal")
    private Integer loginGagal;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(StatusConstant.ACT);
    }

    @Override
    public boolean isAccountNonLocked() {
        return loginGagal < 3;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
