package com.cn.ruolan.bean.api.dynamic;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by wuyinlei on 2017/8/17.
 *
 * @function 发表动态评论的model
 */
public class DynamicAddCommentModel {

    @Expose
    private String dynamicId;  //动态id

    @Expose
    private String userId;  //发布评论的用户id

    @Expose
    private String replayId;  //被回复的用户的id   可以没有

    @Expose
    private String content;

    @Expose
    private String username;

    @Expose
    private String replayName;

    public static boolean check(DynamicAddCommentModel model) {
        //model不允许为空
        return model != null && Strings.isNullOrEmpty(model.dynamicId)
                || Strings.isNullOrEmpty(model.userId)
                || Strings.isNullOrEmpty(model.content);
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReplayId() {
        return replayId;
    }

    public void setReplayId(String replayId) {
        this.replayId = replayId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReplayName() {
        return replayName;
    }

    public void setReplayName(String replayName) {
        this.replayName = replayName;
    }
}
