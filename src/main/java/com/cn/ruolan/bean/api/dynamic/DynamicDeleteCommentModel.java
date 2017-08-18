package com.cn.ruolan.bean.api.dynamic;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by wuyinlei on 2017/8/18.
 */
public class DynamicDeleteCommentModel {

    @Expose
    private String userId;

    @Expose
    private String commentId;

    @Expose
    private String dynamicId;

    public static boolean check(DynamicDeleteCommentModel model) {
        //model不允许为空
        return model != null && Strings.isNullOrEmpty(model.dynamicId)
                || Strings.isNullOrEmpty(model.userId)
                || Strings.isNullOrEmpty(model.commentId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }
}
