package com.example.zalotest.chat;

public class TinNhanBanBe {
    private String IDPhongChats;
    private String IDFriends;
    private String chat;
    private String hinhAnh;
    private String ghiAm;
    private String NhanDan;
    private String videoCall;
    private String Call;
    private String realTime;
    private String TrangThai;
    private String TypeChats;
    public TinNhanBanBe(){

    }

    public TinNhanBanBe(String IDPhongChats, String IDFriends, String chat) {
        this.IDPhongChats = IDPhongChats;
        this.IDFriends = IDFriends;
        this.chat = chat;
    }


    public TinNhanBanBe(String IDPhongChats, String IDFriends, String chat, String realTime, String TrangThai) {
        this.IDPhongChats = IDPhongChats;
        this.IDFriends = IDFriends;
        this.chat = chat;
        this.realTime = realTime;
        this.TrangThai = TrangThai;
    }

    public TinNhanBanBe(String IDPhongChats, String IDFriends, String chat, String realTime, String TrangThai, String TypeChats) {
        this.IDPhongChats = IDPhongChats;
        this.IDFriends = IDFriends;
        this.chat = chat;
        this.realTime = realTime;
        this.TrangThai = TrangThai;
        this.TypeChats = TypeChats;
    }

    public String getTypeChats() {
        return TypeChats;
    }

    public void setTypeChats(String typeChats) {
        TypeChats = typeChats;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getRealTime() {
        return realTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public String getIDPhongChats() {
        return IDPhongChats;
    }

    public void setIDPhongChats(String IDPhongChats) {
        this.IDPhongChats = IDPhongChats;
    }

    public String getIDFriends() {
        return IDFriends;
    }

    public void setIDFriends(String IDFriends) {
        this.IDFriends = IDFriends;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getGhiAm() {
        return ghiAm;
    }

    public void setGhiAm(String ghiAm) {
        this.ghiAm = ghiAm;
    }

    public String getNhanDan() {
        return NhanDan;
    }

    public void setNhanDan(String nhanDan) {
        NhanDan = nhanDan;
    }

    public String getVideoCall() {
        return videoCall;
    }

    public void setVideoCall(String videoCall) {
        this.videoCall = videoCall;
    }

    public String getCall() {
        return Call;
    }

    public void setCall(String call) {
        Call = call;
    }
}
