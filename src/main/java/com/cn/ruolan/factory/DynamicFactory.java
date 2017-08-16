package com.cn.ruolan.factory;

import com.cn.ruolan.bean.api.dynamic.PublishModel;
import com.cn.ruolan.bean.db.Dynamic;
import com.cn.ruolan.bean.db.User;
import com.cn.ruolan.provider.GsonProvider;
import com.cn.ruolan.utils.Hib;
import org.hibernate.Session;

import java.util.List;


/**
 * Created by wuyinlei on 2017/8/16.
 */
public class DynamicFactory {


    /**
     * 发布动态
     *
     * @param publishId
     * @return
     */
    public static Dynamic publish(User publishId, PublishModel model) {

        Dynamic dynamic =  createDynamic(publishId,model);


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

    private static Dynamic createDynamic(User user, PublishModel model) {
        Dynamic dynamic = new Dynamic(user,model);
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
}
