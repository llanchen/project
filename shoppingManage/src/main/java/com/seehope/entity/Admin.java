package com.seehope.entity;

public class Admin {
    private int adminid;
    private String adminname;
    private String adminpwd;

    public Admin() {
    }

    public Admin(int adminid, String adminname, String adminpwd) {
        this.adminid = adminid;
        this.adminname = adminname;
        this.adminpwd = adminpwd;
    }

    public Admin(String adminname, String adminpwd) {
        this.adminname = adminname;
        this.adminpwd = adminpwd;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }
}
