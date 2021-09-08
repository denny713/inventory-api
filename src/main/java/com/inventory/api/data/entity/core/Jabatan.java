package com.inventory.api.data.entity.core;

import com.inventory.api.data.entity.base.DescEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "jabatan")
public class Jabatan extends DescEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_jabatan")
    private Long idJabatan;

    @Column(name = "id_token")
    private Long idToken;

    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_token", insertable = false, updatable = false, referencedColumnName = "id_token")
    private Token token;

    @OneToMany(mappedBy = "id.idJabatan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @OrderBy("id.idMenu ASC")
    private List<Akses> akses;
}
