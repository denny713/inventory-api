package com.inventory.api.data.entity.core;

import com.inventory.api.data.entity.base.DescEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "jabatan")
public class Jabatan extends DescEntity {

    @Id
    @Column(name = "id_jabatan")
    private Long idJabatan;
}
