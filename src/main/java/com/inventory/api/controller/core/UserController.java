package com.inventory.api.controller.core;

import com.inventory.api.controller.base.BaseController;
import com.inventory.api.data.entity.core.Pengguna;
import com.inventory.api.data.model.request.InputPengguna;
import com.inventory.api.data.model.response.Response;
import com.inventory.api.util.EncryptUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @PostMapping("")
    @PreAuthorize("@api.cek(2, false)")
    @ResponseBody
    public Response simpanAtauUbahPengguna(@Valid @RequestBody InputPengguna pengguna) throws NoSuchAlgorithmException {


        return null;
    }
}
