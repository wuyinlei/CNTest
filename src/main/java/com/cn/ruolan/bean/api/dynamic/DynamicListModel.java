package com.cn.ruolan.bean.api.dynamic;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by wuyinlei on 2017/8/17.
 */
public class DynamicListModel {

    @Expose
    private String index;  //第几页

    @Expose
    private String count;  //一页多少个

    @Expose
    private String userId;// 当前用户的id

    public static boolean check(DynamicListModel model) {
        return model != null && Strings.isNullOrEmpty(model.index)
                || Strings.isNullOrEmpty(model.count)
                || Strings.isNullOrEmpty(model.userId);
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPublishId() {
        return userId;
    }

    public void setPublishId(String publishId) {
        this.userId = publishId;
    }
}
