package com.cn.ruolan.bean.api.account;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by wuyinlei on 2017/8/16.
 */
public class RegModel {
    @Expose
    private String username;
    @Expose
    private String phone;
    @Expose
    private String password;

    /**
     * 校验
     * @param model  RegisterModel
     * @return  false  true
     */
    public static boolean check(RegModel model) {

        return model != null
                && Strings.isNullOrEmpty(model.phone)
                && Strings.isNullOrEmpty(model.password)
                && Strings.isNullOrEmpty(model.username);
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
}
