package com.cn.ruolan.bean.api.dynamic;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by wuyinlei on 2017/8/16.
 *
 * @function  发布动态的model
 */
public class PublishModel {


    @Expose
    private String content;

    @Expose
    private String publishId;

    @Expose
    private List<String> pictures;

    public static boolean check(PublishModel model) {

        //model不允许为空
        return model != null && Strings.isNullOrEmpty(model.content)
                        || Strings.isNullOrEmpty(model.publishId);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }


    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<String> getPictures() {
        return pictures;
    }
}
