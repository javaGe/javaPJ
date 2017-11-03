package com.ggf.novel.entity;

public class User {
    private String uid;

    private String name;

    private String password;

    private String email;

    private String activateCode;

    private Integer status;

    public User(String uid, String name, String password, String email, String activateCode, Integer status) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.activateCode = activateCode;
        this.status = status;
    }

    public User() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode == null ? null : activateCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}