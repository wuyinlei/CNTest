package com.cn.ruolan.bean.api.dynamic;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by wuyinlei on 2017/8/17.
 */
public class DynamicDeleteModel {

    @Expose
    private String dynamicId;
    @Expose
    private String publishId;

    public static boolean check(DynamicDeleteModel model) {

        //model不允许为空
        return model != null && Strings.isNullOrEmpty(model.dynamicId)
                || Strings.isNullOrEmpty(model.publishId);
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }
}
