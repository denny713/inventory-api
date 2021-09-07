package com.inventory.api.enumeration;

import com.inventory.api.message.Pesan;

public enum PesanEnum implements Pesan {

    SUKSES_SIMPAN("Berhasil Simpan Data Dengan Kode %s"),
    GAGAL_SIMPAN("Gagal Simpan Data"),
    SUKSES_UBAH("Data Dengan Kode %s Berhasil Diubah"),
    GAGAL_UBAH("Data Dengan Kode %s Gagal Diubah"),
    SUKSES_HAPUS("Data Dengan Kode %s Berhasil Dihapus"),
    GAGAL_HAPUS("Data Dengan Kode %s Gagal Dihapus"),
    SUKSES_LOGIN("Login Berhasil"),
    GAGAL_LOGIN("Login Gagal"),
    SUKSES_LOGOUT("Berhasil Logout"),
    GAGAL_LOGOUT("Gagal Logout"),
    AKUN_TERKUNCI("Akun Anda Terkunci Karena Sudah 3 kali Gagal Login, Hubungi Administrator Untuk Membuka Kembali Akun Anda"),
    TIDAK_SESUAI("%s Tidak Sesuai"),
    DATA_TIDAK_DITEMUKAN("Data %s Tidak Ditemukan");

    private String message;

    PesanEnum(String message) {
        this.message = message;
    }

    public PesanEnum formatPesan(Object... args) {
        this.message = String.format(this.message, args);
        return this;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getMessageName() {
        return this.name();
    }
}
