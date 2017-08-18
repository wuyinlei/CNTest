package com.cn.ruolan.bean.card;

import com.google.gson.annotations.Expose;

/**
 * Created by wuyinlei on 2017/8/18.
 *
 * @function 基础类
 */
public class BaseModel {

    @Expose
    private String message;

    @Expose
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
