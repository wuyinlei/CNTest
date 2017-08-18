package com.cn.ruolan.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by wuyinlei on 2017/8/15.
 */

@Entity
@Table(name = "CN_USER")
public class User {

    @Id  //主键
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")  //UUID类型
    @Column(nullable = false, updatable = false, insertable = false, unique = false)
    @GenericGenerator(name = "uuid", strategy = "uuid2")  //把uuid的生成器定义为uuid2  uuid2在hibernate中是常规的UUID
    private String userId;

    @Column(nullable = false,unique = true,length = 128)
    private String username;  //用户名   不能为空  唯一  长度限制

    @Column(nullable = false,unique = true,length = 60)
    private String phone;  //手机号  不能为空  唯一  长度限制

    @Column(nullable = false,length = 128)
    private String password;  //密码  不能为空

    @Column
    private String description;  //描述  允许为空

    @Column
    private String token;   //唯一token  可以做到单点登录

    @Column //允许为空
    private String portrait = "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg";

    @Column(nullable = false)  //有初始值  不为空
    private int sex = 0;

    @CreationTimestamp   //定义为创建时间戳  在创建时候就已经写入
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();  //当前创建时间

    @UpdateTimestamp   //更新时间戳  在创建时候就已经写入
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();  //更新时间

//    //我发布的动态   对应的是动态里面的id
//    @JoinColumn(name = "publishId")   //懒加载  加载用户信息的时候不加载这个集合
//    @LazyCollection(LazyCollectionOption.EXTRA)  //懒加载集合  尽可能的不加载具体的数据
//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Dynamic> dynamics = new HashSet<>();


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

//    public void setDynamics(Set<Dynamic> dynamics) {
//        this.dynamics = dynamics;
//    }
//
//    public Set<Dynamic> getDynamics() {
//        return dynamics;
//    }
}
