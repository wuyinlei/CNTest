package com.cn.ruolan.factory;

import com.cn.ruolan.bean.db.User;
import com.cn.ruolan.utils.Hib;
import com.cn.ruolan.utils.TextUtil;
import org.hibernate.Session;

import java.util.UUID;

/**
 * Created by wuyinlei on 2017/8/16.
 */
public class UserFactory {


    //通过手机号查询用户
    public static User findByPhone(String phone) {

        return Hib.query(new Hib.QueryResult<User>() {
            @Override
            public User query(Session session) {

                User user = (User) session.createQuery("from User where phone =:phonenum")
                        .setParameter("phonenum", phone)
                        .uniqueResult();

                return user;
            }
        });
    }

    /**
     * 通过用户id查询用户
     * @param userId  用户id
     * @return User
     */
    public static User findById(String userId) {

        return Hib.query(new Hib.QueryResult<User>() {
            @Override
            public User query(Session session) {

               return (User) session.createQuery("from User where userId=:userId")
                        .setParameter("userId",userId)
                        .setMaxResults(1)
                        .uniqueResult();

            }
        });
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名  这个唯一
     * @return User
     */
    public static User findByName(String username) {

        return Hib.query(new Hib.QueryResult<User>() {
            @Override
            public User query(Session session) {
                User user = (User) session.createQuery("from User where username =:name")
                        .setParameter("name", username)
                        .uniqueResult();
                return user;
            }
        });
    }

    /**
     * 根据唯一token查询用户
     *
     * @param token 当前用户的token
     * @return User
     */
    public static User findByToken(String token) {

        return Hib.query(new Hib.QueryResult<User>() {
            @Override
            public User query(Session session) {
                User user = (User) session.createQuery("from User where token =:token")
                        .setParameter("token", token)
                        .uniqueResult();
                return user;
            }
        });
    }


    public static User register(String username, String phone, String password) {

        username = username.trim();
        password = encodePassword(password);

        //进行数据库操作
        Session session = Hib.session();

        session.beginTransaction();  //开启一个事物

        User user = createUser(username, phone, password);

        if (user != null) {
            user = login(user);
        }

        return user;
    }


    /**
     * 创建user
     *
     * @param username 用户名称
     * @param phone    手机号
     * @param password 密码
     * @return
     */
    private static User createUser(String username, String phone, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);


        return Hib.query(new Hib.QueryResult<User>() {

            @Override
            public User query(Session session) {
                session.save(user);
                return user;
            }
        });
    }

    private static String encodePassword(String password) {

        password = password.trim();
        //进行MD5加密
        password = TextUtil.getMD5(password);

        return TextUtil.encodeBase64(password);
    }

    public static User login(String account, String password) {

        String accountStr = account.trim();
        //把原文进行同样的密码加密处理  然后进行匹配
        String encodePassword = encodePassword(password);

        User user = Hib.query(new Hib.QueryResult<User>() {
            @Override
            public User query(Session session) {

                return (User) session.createQuery("from User where phone = :phone and password = :password")
                        .setParameter("phone", accountStr)
                        .setParameter("password", encodePassword)
                        .uniqueResult();
            }
        });

        if (user != null)
            user = login(user);

        return user;
    }

    /**
     * 把一个User进行登录操作
     * 本质上是对Token的更新
     *
     * @param user user
     * @return user
     */
    public static User login(User user) {
        String newToken = UUID.randomUUID().toString();   //使用一个随机的UUID值充当Token
        newToken = TextUtil.encodeBase64(newToken); //进行一次base64操作
        user.setToken(newToken);
        return update(user);
    }

    private static User update(User user) {

        return Hib.query(new Hib.QueryResult<User>() {
            @Override
            public User query(Session session) {
                session.saveOrUpdate(user);
                return user;
            }
        });
    }
}
