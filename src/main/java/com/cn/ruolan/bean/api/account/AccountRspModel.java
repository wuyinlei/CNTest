package com.cn.ruolan.bean.api.account;

import com.cn.ruolan.bean.card.UserCard;
import com.cn.ruolan.bean.db.User;
import com.google.gson.annotations.Expose;

/**
 * Created by wuyinlei on 2017/6/10.
 * <p>
 * 账户返回的Model
 */
public class AccountRspModel {
    //用户基本信息
    @Expose
    private UserCard user;
    //当前登录的账号
    @Expose
    private String account;
    //当前登录成功后获取的token   可以通过token获取用户所有的信息
    @Expose
    private String token;


    public AccountRspModel(User user) {
        this.user = new UserCard(user);
        this.account = user.getPhone();
        this.token = user.getToken();
    }

    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
