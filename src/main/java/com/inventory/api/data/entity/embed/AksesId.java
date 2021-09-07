package com.inventory.api.data.entity.embed;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class AksesId implements Serializable {

    private static final long serialVersionUID = 1L;

    public AksesId() {
        super();
    }

    public AksesId(Long idJabatan, Long idMenu) {
        this.idJabatan = idJabatan;
        this.idMenu = idMenu;
    }

    @Column(name = "id_jabatan")
    private Long idJabatan;

    @Column(name = "id_menu")
    private Long idMenu;
}
