package com.cn.ruolan.bean.db;

import com.cn.ruolan.bean.api.dynamic.PublishModel;
import com.cn.ruolan.provider.GsonProvider;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by wuyinlei on 2017/8/16.
 */

@Entity
@Table(name = "CN_DYNAMIC")
public class Dynamic {

    @Id
    @PrimaryKeyJoinColumn
    //避免复杂的服务器和客户端的映射关系
    //这里不自动生成UUID  ID由代码写入  由客户端负责生成
    @GeneratedValue(generator = "uuid")   //UUID的类型  主键生成的存储的类型
    @GenericGenerator(name = "uuid", strategy = "uuid2")  //把uuid的生成器定义为uuid2  uuid2在hibernate中是常规的UUID
    @Column(unique = false, nullable = false)  //不能更改 不允许为空
    private String id;

    //内容不允许为空  类型为text
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;


    @Column
    private String pictures;  //动态图片  可以为空

    @Column(updatable = false, nullable = false)
    private String avatar;  //动态图片  可以为空

    @Column(updatable = false, nullable = false)
    private String username;  //动态图片  可以为空


    @Column(nullable = false, updatable = true)
    private String viewCount;  //动态图片  可以为空

//    //发布动态的人员
//    @JoinColumn(name = "publishId")
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)  //多对一  你可以关注很多人   每一次关注都是一条数据   可以创建很多个关注的信息
//    private User publish;

    //这个字段仅仅只是为了对应sender的数据库字段senderId
    //不允许手动的更新和插入
    @Column(nullable = false)
    private String publishId;

    @Column
    private String likedCount;

    @Column
    private String commentSum;


    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime mCreatedAt = LocalDateTime.now();

    @CreationTimestamp   //定义为创建时间戳  在创建时候就已经写入
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();  //当前创建时间

    @UpdateTimestamp   //更新时间戳  在创建时候就已经写入
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();  //更新时间

    public Dynamic(PublishModel model) {
        this.content = model.getContent();
//        this.publish = sender;
        this.content = model.getContent();
        this.pictures = GsonProvider.getGson().toJson(model.getPictures());
        this.publishId = model.getPublishId();
    }

    public Dynamic() {

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

//    public User getPublish() {
//        return publish;
//    }
//
//    public void setPublish(User publish) {
//        this.publish = publish;
//    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public LocalDateTime getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        mCreatedAt = createdAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }


    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(String commentSum) {
        this.commentSum = commentSum;
    }

    public String getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(String likedCount) {
        this.likedCount = likedCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getViewCount() {
        return viewCount;
    }
}

