package com.cn.ruolan.factory;

import com.cn.ruolan.bean.api.dynamic.DynamicAddLikeModel;
import com.cn.ruolan.bean.api.dynamic.DynamicAddCommentModel;
import com.cn.ruolan.bean.api.dynamic.DynamicDeleteCommentModel;
import com.cn.ruolan.bean.api.dynamic.PublishModel;
import com.cn.ruolan.bean.card.DynamicRspModel;
import com.cn.ruolan.bean.db.Comment;
import com.cn.ruolan.bean.db.Dynamic;
import com.cn.ruolan.bean.db.DynamicLiked;
import com.cn.ruolan.bean.db.User;
import com.cn.ruolan.utils.Hib;
import com.google.common.base.Strings;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
     * @param model
     * @param self
     * @return
     */
    public static Dynamic publish(PublishModel model, User self) {

        Dynamic dynamic = createDynamic(model, self);


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

    private static Dynamic createDynamic(PublishModel model, User user) {
        Dynamic dynamic = new Dynamic(model);

        dynamic.setAvatar(user.getPortrait());
        dynamic.setUsername(user.getUsername());
        dynamic.setViewCount("1");
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
    public static Comment comment(DynamicAddCommentModel model) {

        Comment comment = new Comment();

        comment.setDynamicId(model.getDynamicId());  //动态id

        if (!Strings.isNullOrEmpty(model.getReplayId())) {
            //有回复的id
            comment.setReplayId(model.getUserId());   //被回复的的当前评论的用户id
            comment.setCommentId(model.getReplayId());    //发表评论的用户id
            comment.setReplayContent(model.getContent());  //发表的评论
            comment.setReplayName(model.getReplayName());
            comment.setNickname(model.getUsername());


        } else {
            //没有回复的id
            comment.setCommentId(model.getUserId());  //发表的评论用户id
            comment.setCommentContent(model.getContent());  //发表的内容
            comment.setNickname(model.getUsername());
        }

        return Hib.query(new Hib.QueryResult<Comment>() {
            @Override
            public Comment query(Session session) {
                session.save(comment);
                return comment;
            }
        });
    }

    public static List<Dynamic> queryList(String index, String count) {


        int page = Integer.parseInt(index);  //当前页
        int size = Integer.parseInt(count);  //每页数据大小

        page = (page - 1) * size;

        int finalPage = page;

        return Hib.query(new Hib.QueryResult<List<Dynamic>>() {
            @Override
            public List<Dynamic> query(Session session) {

                return session.createQuery("from Dynamic ")
                        .setFirstResult(finalPage)
                        .setMaxResults(size)
                        .list();

            }
        });
    }

    /**
     * 转换动态集合数据
     *
     * @param dynamics  所有的动态  这个是分页来的
     * @param publishId 当前用户id  用来判断是否收藏过该动态
     * @return 需要返给客户端的数据集合
     */
    public static List<DynamicRspModel> formatDynamic(List<Dynamic> dynamics, String publishId) {

        List<DynamicRspModel> dynamicRspModels = new ArrayList<>();

        for (Dynamic dynamic : dynamics) {
            DynamicRspModel model = new DynamicRspModel();
            model.setAvatar(dynamic.getAvatar());
            model.setDynamicId(dynamic.getId());
            model.setPhotos(dynamic.getPictures());
            model.setUsername(dynamic.getUsername());
            model.setCreateData(dynamic.getCreateAt().toString());
            model.setViewCount(dynamic.getViewCount());
            model.setDynamicContent(dynamic.getContent());
            model.setUserId(dynamic.getPublishId());

            List<DynamicLiked> dynamicLikeds = queryLiked(dynamic.getId());

            if (dynamicLikeds != null) {

                //赞逻辑
                int likedsize = dynamicLikeds.size();

                likedsize = likedsize > 30 ? 30 : likedsize;

                List<DynamicRspModel.Liked> likeds = new ArrayList<>();

                for (int i = 0; i < likedsize; i++) {
                    DynamicRspModel.Liked liked = new DynamicRspModel.Liked();
                    DynamicLiked dynamicLiked = dynamicLikeds.get(i);
                    liked.setAvatar(dynamicLiked.getAvatar());
                    liked.setUserId(dynamicLiked.getUserId());
                    likeds.add(liked);
                }

                //设置点赞个数
                model.setLikeds(likeds);
            }

            //设置当前登录的用户是否点赞过该动态
            model.setLiked(dynamic.getPublishId().equals(publishId));

            model.setLikeCount(dynamicLikeds.size() + "");

            List<Comment> comments = queryComment(dynamic.getId());

            if (comments != null) {

                List<DynamicRspModel.Comments> commentsList = new ArrayList<>();

                for (Comment comment : comments) {
                    DynamicRspModel.Comments commentResult = new DynamicRspModel.Comments();
                    commentResult.setCommentId(comment.getCommentId());
                    commentResult.setContent(comment.getCommentContent());
                    if (comment.getReplayName() != null) {
                        commentResult.setNickname(comment.getNickname());
                        commentResult.setReplyNickname(comment.getReplayName());
                        commentResult.setUserId(comment.getCommentId());
                        commentResult.setReplyUserid(comment.getReplayId());
                        commentResult.setContent(comment.getReplayContent());
                    } else {
                        commentResult.setNickname(comment.getNickname());
                        commentResult.setUserId(comment.getCommentId());
                    }
                    commentsList.add(commentResult);
                }

                model.setCommentCount(commentsList.size() + "");
                model.setComments(commentsList);
            } else {
                //没有评论

            }


            dynamicRspModels.add(model);

        }

        return dynamicRspModels;
    }


    /**
     * 查看该动态的喜欢收藏人数
     *
     * @param dynamicId 动态id
     * @return 喜欢集合
     */
    private static List<DynamicLiked> queryLiked(String dynamicId) {

        return Hib.query(new Hib.QueryResult<List<DynamicLiked>>() {
            @Override
            public List<DynamicLiked> query(Session session) {

                return session.createQuery("from DynamicLiked where dynamicId=:dynamicId")
                        .setParameter("dynamicId", dynamicId)
//                        .setMaxResults(30)
                        .list();

            }
        });
    }

    /**
     * 查询该动态的所有评论
     *
     * @param dynamicId 动态id
     * @return 评论集合
     */
    private static List<Comment> queryComment(String dynamicId) {

        return Hib.query(new Hib.QueryResult<List<Comment>>() {
            @Override
            public List<Comment> query(Session session) {

                return session.createQuery("from Comment where dynamicId = :dynamicId")
                        .setParameter("dynamicId", dynamicId)
//                        .setMaxResults(30)
                        .list();

            }
        });
    }

    /**
     * 更新动态的查看数
     *
     * @param dynamics
     */
    public static void updateDynamicViewCount(List<Dynamic> dynamics) {
        for (Dynamic dynamic : dynamics) {

            Hib.queryOnly(new Hib.QueryOnly() {
                @Override
                public void query(Session session) {

                    int currentCount = Integer.parseInt(dynamic.getViewCount());

                    int random = new Random().nextInt(30) + currentCount;

                    dynamic.setViewCount(String.valueOf(random));

                    session.saveOrUpdate(dynamic);
                }
            });

        }

    }


    public static DynamicLiked addLike(DynamicAddLikeModel model, User user) {

        DynamicLiked dynamicLiked = new DynamicLiked();
        dynamicLiked.setDynamicId(model.getDynamicId());
        dynamicLiked.setUserId(model.getUserId());

//        User user = UserFactory.findById(model.getUserId());
        dynamicLiked.setAvatar(user.getPortrait());
        dynamicLiked.setNickname(user.getUsername());

        return Hib.query(new Hib.QueryResult<DynamicLiked>() {
            @Override
            public DynamicLiked query(Session session) {
                session.save(dynamicLiked);
                return dynamicLiked;
            }
        });

    }

    public static DynamicLiked findDynamicLikedByDynamicId(String dynamicId, String userId) {

        return Hib.query(new Hib.QueryResult<DynamicLiked>() {
            @Override
            public DynamicLiked query(Session session) {

                return (DynamicLiked) session.createQuery("from DynamicLiked where dynamicId = :dynamicId and userId = :userId")
                        .setParameter("dynamicId", dynamicId)
                        .setParameter("userId", userId)
                        .setMaxResults(1)
                        .uniqueResult();
//                return null;
            }
        });


    }

    public static DynamicLiked hideLike(DynamicAddLikeModel model) {

        return Hib.query(new Hib.QueryResult<DynamicLiked>() {
            @Override
            public DynamicLiked query(Session session) {
                session.delete(findDynamicLikedByDynamicId(model.getDynamicId(), model.getUserId()));

                return findDynamicLikedByDynamicId(model.getDynamicId(), model.getUserId());
            }
        });

    }

    public static Comment findComment(String commentId) {


        return Hib.query(new Hib.QueryResult<Comment>() {
            @Override
            public Comment query(Session session) {

                return (Comment) session.createQuery("from Comment where id=:commentId")
                        .setParameter("commentId", commentId)
                        .setMaxResults(1)
                        .uniqueResult();

            }
        });

    }

    public static Comment deleteComment(DynamicDeleteCommentModel model) {

        return Hib.query(new Hib.QueryResult<Comment>() {
            @Override
            public Comment query(Session session) {


                session.delete(findComment(model.getCommentId()));

                //如果找不到就是删除成功
                Comment comment = findComment(model.getCommentId());

                return comment;
            }
        });

    }
}
