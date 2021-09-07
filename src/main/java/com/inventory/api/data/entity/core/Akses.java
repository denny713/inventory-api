package com.inventory.api.data.entity.core;

import com.inventory.api.data.entity.embed.AksesId;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "akses")
public class Akses implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AksesId id;

    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_menu", insertable = false, updatable = false, referencedColumnName = "id_menu")
    private Menu menu;
}
