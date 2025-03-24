package com.example.zalotest;

import java.io.Serializable;

public class DanhSachBanBe implements Serializable {
    private String idTaiKhoanChinh;
    private String IDPhongChat;
    private String soDienThoai;
    private String tenNguoiDung;
    private String ngaySinh;
    private String gioiTinh;
    private String imageAnhDaiDien;
    private String imageBia;
    private String matKhau;
    private String QR;
    private String TinNhan;
    private int STT;

    public DanhSachBanBe(){

    }

    public DanhSachBanBe(String idTaiKhoanChinh, String soDienThoai, String tenNguoiDung) {
        this.idTaiKhoanChinh = idTaiKhoanChinh;
        this.soDienThoai = soDienThoai;
        this.tenNguoiDung = tenNguoiDung;
    }

    public DanhSachBanBe(String idTaiKhoanChinh, String IDPhongChat, String tenNguoiDung, int STT) {
        this.idTaiKhoanChinh = idTaiKhoanChinh;
        this.IDPhongChat = IDPhongChat;
        this.tenNguoiDung = tenNguoiDung;
        this.STT = STT;
    }

    public DanhSachBanBe(String idTaiKhoanChinh, String IDPhongChat, String tenNguoiDung, int STT, String TinNhan) {
        this.idTaiKhoanChinh = idTaiKhoanChinh;
        this.IDPhongChat = IDPhongChat;
        this.tenNguoiDung = tenNguoiDung;
        this.STT = STT;
        this.TinNhan = TinNhan;
    }

    public DanhSachBanBe(String idTaiKhoanChinh, String soDienThoai, String tenNguoiDung, String ngaySinh, String gioiTinh, String imageAnhDaiDien, String imageBia, String matKhau, String QR) {
        this.idTaiKhoanChinh = idTaiKhoanChinh;
        this.soDienThoai = soDienThoai;
        this.tenNguoiDung = tenNguoiDung;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.imageAnhDaiDien = imageAnhDaiDien;
        this.imageBia = imageBia;
        this.matKhau = matKhau;
        this.QR = QR;
    }

    public String getIDPhongChat() {
        return IDPhongChat;
    }

    public void setIDPhongChat(String IDPhongChat) {
        this.IDPhongChat = IDPhongChat;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getTinNhan() {
        return TinNhan;
    }

    public void setTinNhan(String tinNhan) {
        TinNhan = tinNhan;
    }

    public String getIdTaiKhoanChinh() {
        return idTaiKhoanChinh;
    }

    public void setIdTaiKhoanChinh(String idTaiKhoanChinh) {
        this.idTaiKhoanChinh = idTaiKhoanChinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getImageAnhDaiDien() {
        return imageAnhDaiDien;
    }

    public void setImageAnhDaiDien(String imageAnhDaiDien) {
        this.imageAnhDaiDien = imageAnhDaiDien;
    }

    public String getImageBia() {
        return imageBia;
    }

    public void setImageBia(String imageBia) {
        this.imageBia = imageBia;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQR() {
        return QR;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

}
