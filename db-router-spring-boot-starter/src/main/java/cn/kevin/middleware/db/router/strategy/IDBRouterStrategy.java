package cn.kevin.middleware.db.router.strategy;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface IDBRouterStrategy {

    /**
     * 路由计算
     * @param dbKey
     * @return
     */
    void doRouter(String dbKey);


    /**
     * 清空路由信息
     */
    void clear();
}
