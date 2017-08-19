package com.cn.ruolan.service;

import com.cn.ruolan.ResponseModel;
import com.cn.ruolan.bean.api.dynamic.*;
import com.cn.ruolan.bean.card.BaseModel;
import com.cn.ruolan.bean.card.DynamicCard;
import com.cn.ruolan.bean.card.DynamicRspModel;
import com.cn.ruolan.bean.db.Comment;
import com.cn.ruolan.bean.db.Dynamic;
import com.cn.ruolan.bean.db.DynamicLiked;
import com.cn.ruolan.bean.db.User;
import com.cn.ruolan.factory.DynamicFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by wuyinlei on 2017/8/16.
 *
 * @function 动态的相关接口
 */
@Path("/dynamic")
public class DynamicService extends BaseService{

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

        User self = getSelf();
        if (self == null)
            return ResponseModel.buildNoAuthorError();

        Dynamic dynamic = DynamicFactory.publish(model,self);

        if (dynamic == null)
            return ResponseModel.buildServiceError();

        DynamicCard dynamicCard = new DynamicCard(dynamic,self);

        return ResponseModel.buildOk(dynamicCard);

    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<DynamicCard> delete(DynamicDeleteModel model) {


        if (DynamicDeleteModel.check(model))
            return ResponseModel.buildParameterError();

        User self = getSelf();
        if (self == null)
            return ResponseModel.buildNoAuthorError();

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
    public ResponseModel<List<DynamicRspModel>> list(DynamicListModel model) {

        if (DynamicListModel.check(model))
            return ResponseModel.buildParameterError();

        List<Dynamic> dynamics = DynamicFactory.queryList(model.getIndex(), model.getCount());

        DynamicFactory.updateDynamicViewCount(dynamics);

        List<DynamicRspModel> models = DynamicFactory.formatDynamic(dynamics, model.getPublishId());

        return ResponseModel.buildOk(models);

    }

    @POST
    @Path("/add/comment")  //发布评论的接口
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<DynamicCard> addComment(DynamicAddCommentModel model) {

        if (DynamicAddCommentModel.check(model))
            return ResponseModel.buildParameterError();

        User self = getSelf();
        if (self == null)
            return ResponseModel.buildNoAuthorError();

        Comment comment = DynamicFactory.comment(model);

        if (comment == null)
            return ResponseModel.buildServiceError();

        String id = comment.getId();

        return new ResponseModel<>(1, id);
    }


    @POST
    @Path("/delete/comment")  //发布评论的接口
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<BaseModel> deleteComment(DynamicDeleteCommentModel model) {

        User self = getSelf();
        if (self == null)
            return ResponseModel.buildNoAuthorError();

        if (DynamicDeleteCommentModel.check(model))
            return ResponseModel.buildParameterError();


        Comment comment = DynamicFactory.deleteComment(model);


        if (comment != null) {

            return ResponseModel.buildDeletCommentError();
        } else {

            return ResponseModel.buildOk();
        }

    }


    @POST
    @Path("/addlike")  //发布评论的接口
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<BaseModel> addLike(DynamicAddLikeModel model) {

        User self = getSelf();
        if (self == null)
            return ResponseModel.buildNoAuthorError();

        if (DynamicAddLikeModel.check(model))
            return ResponseModel.buildParameterError();

        DynamicLiked dynamicLiked = DynamicFactory.addLike(model,self);

        if (dynamicLiked == null)
            return ResponseModel.buildAddLikedError();


        return ResponseModel.buildOk();

    }

    @POST
    @Path("/hidelike")  //发布评论的接口
    @Consumes(MediaType.APPLICATION_JSON)  //指定请求返回的响应体为JSON
    @Produces(MediaType.APPLICATION_JSON)  // JSON 形式将对象返回给客户端
    public ResponseModel<BaseModel> hideLike(DynamicAddLikeModel model) {


        User self = getSelf();
        if (self == null)
            return ResponseModel.buildNoAuthorError();

        if (DynamicAddLikeModel.check(model))
            return ResponseModel.buildParameterError();


        DynamicLiked dynamicLiked = DynamicFactory.hideLike(model);

        BaseModel baseModel = new BaseModel();

        if (dynamicLiked != null) {
            baseModel.setCode(-1);
            baseModel.setMessage("Hide Error");
            return ResponseModel.buildOk(baseModel);
        } else {

            return ResponseModel.buildOk();

        }

    }


}
