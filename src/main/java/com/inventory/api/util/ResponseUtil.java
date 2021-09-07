package com.inventory.api.util;

import com.inventory.api.data.model.response.Response;
import org.springframework.http.HttpStatus;

public class ResponseUtil {

    private ResponseUtil() {
        super();
    }

    public static <T> Response setSuccess(int kode, String pesan, T data) {
        return new Response(kode, buildMessage(kode, pesan), data);
    }

    public static Response setFailed(int kode, String pesan) {
        return new Response(kode, buildMessage(kode, pesan), null);
    }

    public static String buildMessage(int code, String msg) {
        return HttpStatus.valueOf(code).getReasonPhrase() + " : " + msg;
    }
}
