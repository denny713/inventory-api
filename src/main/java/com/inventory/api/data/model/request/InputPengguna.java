package com.inventory.api.data.model.request;

import lombok.Data;

@Data
public class InputPengguna {

    private String username;
    private String nama;
    private String password;
    private String cabang;
    private Long jabatan;
}
