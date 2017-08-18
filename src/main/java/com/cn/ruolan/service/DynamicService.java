package com.cn.ruolan.service;

import com.cn.ruolan.ResponseModel;
import com.cn.ruolan.bean.api.dynamic.DynamicCommentModel;
import com.cn.ruolan.bean.api.dynamic.DynamicDeleteModel;
import com.cn.ruolan.bean.api.dynamic.PublishModel;
import com.cn.ruolan.bean.card.DynamicCard;
import com.cn.ruolan.bean.db.Comment;
import com.cn.ruolan.bean.db.Dynamic;
import com.cn.ruolan.bean.db.User;
import com.cn.ruolan.factory.DynamicFactory;
import com.cn.ruolan.factory.UserFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by wuyinlei on 2017/8/16.
 */
@Path("/dynamic")
public class DynamicService {

    //添加一个上下文注解  该注解会给 mSecurityContext赋值  具体的值为我们的拦截器中所返回的上下文
    @Context
    protected SecurityContext mSecurityContext;


    @POST
    @Path("/publish")
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<DynamicCard> publish(PublishModel model) {

        if (model.check(model))
            return ResponseModel.buildParameterError();


        Dynamic dynamic = DynamicFactory.publish(model);

        if (dynamic == null)
            return ResponseModel.buildServiceError();

        DynamicCard dynamicCard = new DynamicCard(dynamic);

        return ResponseModel.buildOk(dynamicCard);

    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<DynamicCard> delete(DynamicDeleteModel model) {

        if (DynamicDeleteModel.check(model))
            return ResponseModel.buildParameterError();

        Dynamic dynamic = DynamicFactory.findById(model.getDynamicId());

        if (dynamic == null)
            return ResponseModel.buildDynamicNotExist();

        if (!model.getPublishId().equals(dynamic.getPublishId()))
            return ResponseModel.buildNoAuthorError();

        DynamicFactory.delete(model.getPublishId());

        return ResponseModel.buildOk();

    }

    @POST
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<DynamicCard> list(DynamicDeleteModel model) {



        return null;
    }

    @POST
    @Path("/comment")  //发布评论的接口
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<DynamicCard> comment(DynamicCommentModel model) {

        if (DynamicCommentModel.check(model))
            return ResponseModel.buildParameterError();

        Comment comment = DynamicFactory.comment(model);

        if (comment == null)
            return ResponseModel.buildServiceError();

        String id = comment.getId();

        return new ResponseModel<>(1, id);
    }


}
