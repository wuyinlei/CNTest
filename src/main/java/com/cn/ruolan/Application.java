package com.cn.ruolan;

import com.cn.ruolan.provider.GsonProvider;
import com.cn.ruolan.service.AccountService;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

/**
 * Created by wuyinlei on 2017/8/15.
 */
public class Application extends ResourceConfig {



    public Application() {
        //注册逻辑处理的包名  以下两种方式都是可以的
        packages("com.cn.ruolan.service");
//        packages(AccountService.class.getPackage().getName());

        //注册拦截器
//        register(AuthRequestFilter.class);

        //注册Json转换器
        register(GsonProvider.class);   //替换解析器为Gson
//        register(JacksonJaxbJsonProvider.class);  //默认的Jackson解析器

        //注册日志打印输出
        register(Logger.class);
    }

}
