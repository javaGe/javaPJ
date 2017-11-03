package com.ggf.novel.entity;

import java.util.Date;

public class Story {
    private String id;

    private String cid;

    private String name;

    private String author;

    private String categoryName;

    private String intro;

    private Date createTime;

    private Date modifyTime;

    public Story(String id, String cid, String name, String author, String categoryName, String intro, Date createTime, Date modifyTime) {
        this.id = id;
        this.cid = cid;
        this.name = name;
        this.author = author;
        this.categoryName = categoryName;
        this.intro = intro;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Story() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}