package com.inventory.api.data.entity.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class DescEntity extends BaseEntity {

    @Column(name = "deskripsi")
    protected String deskripsi;
}
