package com.inventory.api.data.model.response;

import lombok.Data;

@Data
public class Response<T> {

    public Response(int kode, String pesan, T data) {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }

    private int kode;
    private String pesan;
    private T data;
}
