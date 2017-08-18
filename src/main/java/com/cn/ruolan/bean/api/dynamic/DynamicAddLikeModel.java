package com.cn.ruolan.bean.api.dynamic;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by wuyinlei on 2017/8/18.
 *
 * @function 添加点赞的model
 */
public class DynamicAddLikeModel {

    @Expose
    private String userId;

    @Expose
    private String dynamicId;

    public static boolean check(DynamicAddLikeModel model) {
        //model不允许为空
        return model != null && Strings.isNullOrEmpty(model.userId)
                || Strings.isNullOrEmpty(model.dynamicId);
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }
}
