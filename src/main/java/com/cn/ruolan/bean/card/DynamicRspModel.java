package com.cn.ruolan.bean.card;

import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by wuyinlei on 2017/8/18.
 */
public class DynamicRspModel {

    @Expose
    private String dynamicId; //动态的id

    @Expose
    private String dynamicContent; //动态详情

    @Expose
    private String viewCount;  //查看数

    @Expose
    private String likeCount; //喜欢数

    @Expose
    private boolean isLiked;  //是否是喜欢  对于当前用户来说

    @Expose
    private String commentCount;  //评论数量

    @Expose
    private String createData;  //创建时间

    @Expose
    private String createdDateLabel;  //时间  类似微信那个时间

    @Expose
    private String userId;  //发布动态的人的id

    @Expose
    private String username; //发布动态的人的名字

    @Expose
    private String avatar;  //发布动态的人的头像

    @Expose
    private String photos;  //动态朋友圈图片

    @Expose
    private List<Comments> comments;


   public static class Comments{

        @Expose
        private String commentId; //评论id

        @Expose
        private String userId; //评论用户id

        @Expose
        private String nickname;//评论用户名字


        @Expose
        private String replyUserid;  //回复人的用户id

        @Expose
        private String replyNickname;  //回复人的名字

        @Expose
        private String content;//评论的内容


        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getReplyUserid() {
            return replyUserid;
        }

        public void setReplyUserid(String replyUserid) {
            this.replyUserid = replyUserid;
        }

        public String getReplyNickname() {
            return replyNickname;
        }

        public void setReplyNickname(String replyNickname) {
            this.replyNickname = replyNickname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    @Expose
    private List<Liked> mLikeds;

    public List<Liked> getLikeds() {
        return mLikeds;
    }

    public void setLikeds(List<Liked> likeds) {
        mLikeds = likeds;
    }

   public static class Liked{


        @Expose
        private String userId;

        @Expose
        private String avatar;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }


    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public String getCreatedDateLabel() {
        return createdDateLabel;
    }

    public void setCreatedDateLabel(String createdDateLabel) {
        this.createdDateLabel = createdDateLabel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
