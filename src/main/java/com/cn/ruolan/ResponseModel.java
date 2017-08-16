package com.cn.ruolan;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by wuyinlei on 2017/8/16.
 */
public class ResponseModel<T> implements Serializable {


    //成功
    public static final int SUCCEED = 1;

    //请求参数错误
    public static final int ERROR_NOT_FOUND_USER = 1000;


    //创建用户失败
    public static final int ERROR_CREATE_USER = 1001;

    //账户Token错误  需要重新登录
    public static final int ERROR_ACCOUNT_TOKEN = 1002;

    //账户登录失败
    public static final int ERROR_ACCOUNT_LOGIN = 1003;

    //账户注册失败
    public static final int ERROR_ACCOUNT_REGISTER = 1004;

    //账户已经存在
    private static final int ERROR_PARAMETERS_EXIST_ACCOUNT = 1005;

    //姓名已经存在
    private static final int ERROR_PARAMETERS_EXIST_NAME = 1006;

    //服务器出错
    private static final int ERROR_SERVICE = -1;

    //参数出错
    private static final int ERROR_PARAMETERS = 10007;


    @Expose
    private int code;
    @Expose
    private String message;
    @Expose
    private LocalDateTime time = LocalDateTime.now();
    @Expose
    private T result;


    public ResponseModel() {
        code = 1;
        message = "ok";
    }

    public ResponseModel(T result) {
        this();
        this.result = result;
    }

    public ResponseModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseModel(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> ResponseModel<T> buildOk() {
        return new ResponseModel<T>();
    }

    public static <T> ResponseModel<T> buildOk(T result) {
        return new ResponseModel<T>(result);
    }

    public static <T> ResponseModel<T> buildHaveAccountError() {
        return new ResponseModel<T>(ERROR_PARAMETERS_EXIST_ACCOUNT, "Already have this account.");
    }

    public static <T> ResponseModel<T> buildHaveNameError() {
        return new ResponseModel<T>(ERROR_PARAMETERS_EXIST_NAME, "Already have this name.");
    }

    public static <T> ResponseModel<T> buildServiceError() {
        return new ResponseModel<T>(ERROR_SERVICE, "Service Error.");
    }

    public static <T> ResponseModel<T> buildLoginError() {
        return new ResponseModel<T>(ERROR_ACCOUNT_LOGIN, "Account or password error.");
    }

    public static <T> ResponseModel<T> buildParameterError() {
        return new ResponseModel<T>(ERROR_PARAMETERS, "Parameters Error.");
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}