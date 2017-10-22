package com.ggf.ssm.pojo;

import java.io.Serializable;

/**
 * 用户实体类
 * Created by PanYuanJin on 2017/10/10.
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1678640538857193243L;
    private String id;
    private String name;
    private String password;
    private String age;
    private String gender;
    private String phone;
    private String adderss;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }
}
