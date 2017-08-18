package com.cn.ruolan.factory;

import com.cn.ruolan.bean.api.dynamic.DynamicCommentModel;
import com.cn.ruolan.bean.api.dynamic.PublishModel;
import com.cn.ruolan.bean.db.Comment;
import com.cn.ruolan.bean.db.Dynamic;
import com.cn.ruolan.bean.db.User;
import com.cn.ruolan.utils.Hib;
import com.google.common.base.Strings;
import org.hibernate.Session;


/**
 * Created by wuyinlei on 2017/8/16.
 */
public class DynamicFactory {

    public static Dynamic findById(String dynamicId) {
        return Hib.query(new Hib.QueryResult<Dynamic>() {
            @Override
            public Dynamic query(Session session) {

                return (Dynamic) session.createQuery("from Dynamic where id = :id ")
                        .setParameter("id", dynamicId)
                        .setMaxResults(1)
                        .uniqueResult();
            }
        });
    }

    /**
     * 发布动态
     *
     * @param publishId
     * @return
     */
    public static Dynamic publish(PublishModel model) {

        Dynamic dynamic = createDynamic(model);


        return dynamic;
    }

//    private static Dynamic createDynamic(User publishId, String content, List<String> pictures) {
//        Dynamic dynamic = new Dynamic();
//        dynamic.setContent(content);
//        dynamic.setPublishId(publishId);
////        dynamic.setPublish(UserFactory.findById(publishId));
//        dynamic.setPictures(GsonProvider.getGson().toJson(pictures));
//
//        return Hib.query(new Hib.QueryResult<Dynamic>() {
//            @Override
//            public Dynamic query(Session session) {
//                session.load(UserFactory.findById(publishId), publishId);
//                session.save(dynamic);
//                return dynamic;
//            }
//        });
//
//    }

    private static Dynamic createDynamic(PublishModel model) {
        Dynamic dynamic = new Dynamic(model);
//        dynamic.setContent(content);
//        dynamic.setPublishId(publishId);

        return Hib.query(new Hib.QueryResult<Dynamic>() {
            @Override
            public Dynamic query(Session session) {

                session.save(dynamic);
                return dynamic;
            }
        });

    }

    public static void delete(String dynamicId) {

        Dynamic dynamic = findById(dynamicId);

        Session session = Hib.session();

        session.beginTransaction();

        session.delete(dynamic);

        session.getTransaction().commit();

//        Hib.queryOnly(new Hib.QueryOnly() {
//            @Override
//            public void query(Session session) {
//                session.delete(dynamic);
//            }
//        });

    }

    /**
     * 发布评论的方法
     *
     * @param model 参数
     */
    public static Comment comment(DynamicCommentModel model) {

        Comment comment = new Comment();

        comment.setDynamicId(model.getDynamicId());  //动态id

        if (!Strings.isNullOrEmpty(model.getReplayId())) {
            //有回复的id
            comment.setReplayId(model.getUserId());   //被回复的的当前评论的用户id
            comment.setCommentId(model.getReplayId());    //发表评论的用户id
            comment.setReplayContent(model.getContent());  //发表的评论

        } else {
            //没有回复的id
            comment.setCommentId(model.getUserId());  //发表的评论用户id
            comment.setCommentContent(model.getContent());  //发表的内容
        }

        return Hib.query(new Hib.QueryResult<Comment>() {
            @Override
            public Comment query(Session session) {
                session.save(comment);
                return comment;
            }
        });
    }
}
