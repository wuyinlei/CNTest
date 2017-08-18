package com.cn.ruolan.bean.card;

import com.cn.ruolan.bean.db.Dynamic;
import com.cn.ruolan.bean.db.User;
import com.cn.ruolan.factory.UserFactory;
import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by wuyinlei on 2017/8/16.
 */
public class DynamicCard {

    @Expose
    private String id;
    @Expose
    private String content;
    @Expose
    private String pictures;
    @Expose
    private LocalDateTime createAt;
    @Expose
    private String publishId;
    @Expose
    private UserCard user;



    public DynamicCard(Dynamic dynamic,User user) {
        this.id = dynamic.getId();
        this.content = dynamic.getContent();
        this.pictures = dynamic.getPictures();
        this.publishId = dynamic.getPublishId();
        this.user = new UserCard(user);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }
}
