package com.cn.ruolan.service;

import com.cn.ruolan.ResponseModel;
import com.cn.ruolan.bean.api.account.AccountRspModel;
import com.cn.ruolan.bean.api.account.LoginModel;
import com.cn.ruolan.bean.api.account.RegModel;
import com.cn.ruolan.bean.db.User;
import com.cn.ruolan.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by wuyinlei on 2017/8/15.
 */

//127.0.0.1/api/account
@Path("/account")
public class AccountService {

    @GET
    @Path("/login")
    public String get() {
        return "you login success";
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<AccountRspModel> register(RegModel model) {

        if (RegModel.check(model))
            return ResponseModel.buildParameterError();

        User user = UserFactory.findByPhone(model.getPhone());

        if (user != null)
            return ResponseModel.buildHaveAccountError();

        user = UserFactory.findByName(model.getUsername());

        if (user != null)
            return ResponseModel.buildHaveNameError();

        user = UserFactory.register(model.getUsername(), model.getPhone(), model.getPassword());

        if (user == null)
            return ResponseModel.buildServiceError();

        AccountRspModel accountRspModel = new AccountRspModel(user);

        return ResponseModel.buildOk(accountRspModel);

    }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<AccountRspModel> login(LoginModel model) {

        if (model.check(model))  //如果校验不成功  返回参数异常
            return ResponseModel.buildParameterError();

        User user = UserFactory.login(model.getAccount(),model.getPassword());


        if (user == null)
            return ResponseModel.buildLoginError();

        AccountRspModel accountRspModel = new AccountRspModel(user);

        return ResponseModel.buildOk(accountRspModel);

    }


}
