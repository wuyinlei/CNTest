package com.cn.ruolan.bean.api.account;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by wuyinlei on 2017/8/16.
 */
public class LoginModel {

    @Expose
    private String username;  //账户
    @Expose
    private String password;  //密码

    /**
     * 校验
     * @param model  LoginModel
     * @return  false  true
     */
    public static boolean check(LoginModel model) {

        return model != null
                && Strings.isNullOrEmpty(model.username)
                && Strings.isNullOrEmpty(model.password);
    }

    public String getAccount() {
        return username;
    }

    public void setAccount(String account) {
        this.username = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}