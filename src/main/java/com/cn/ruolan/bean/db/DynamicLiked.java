package com.cn.ruolan.bean.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by wuyinlei on 2017/8/18.
 */
@Entity
@Table(name = "CN_DYNAMIC_LIKED")
public class DynamicLiked {


    @Id
    @PrimaryKeyJoinColumn
    //避免复杂的服务器和客户端的映射关系
    //这里不自动生成UUID  ID由代码写入  由客户端负责生成
    @GeneratedValue(generator = "uuid")   //UUID的类型  主键生成的存储的类型
    @GenericGenerator(name = "uuid", strategy = "uuid2")  //把uuid的生成器定义为uuid2  uuid2在hibernate中是常规的UUID
    @Column(unique = false, nullable = false)  //不能更改 不允许为空
    private String id;


    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private String dynamicId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getDynamicId() {
        return dynamicId;
    }
}
