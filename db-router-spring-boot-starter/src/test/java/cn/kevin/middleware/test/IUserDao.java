package cn.kevin.middleware.test;

import cn.kevin.middleware.db.router.annotation.DBRouter;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/2 21:54
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface IUserDao {

    @DBRouter(key = "userId")
    void insertUser(String req);
}
