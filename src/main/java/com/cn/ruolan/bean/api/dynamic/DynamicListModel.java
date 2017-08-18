package com.cn.ruolan.bean.api.dynamic;

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
    private String publishId;// 当前用户的id

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
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }
}
