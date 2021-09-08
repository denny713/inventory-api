package com.inventory.api.data.entity.core;

import com.inventory.api.constant.StatusConstant;
import com.inventory.api.data.entity.base.MasterEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pengguna", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class Pengguna extends MasterEntity implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_user")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "kode_cabang")
    private String kodeCabang;

    @Column(name = "id_jabatan")
    private Long idJabatan;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_jabatan", insertable = false, updatable = false, referencedColumnName = "id_jabatan")
    private Jabatan jabatan;

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
