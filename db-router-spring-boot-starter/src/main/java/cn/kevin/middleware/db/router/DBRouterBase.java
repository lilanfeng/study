package cn.kevin.middleware.db.router;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DBRouterBase {

    private String tbIdx;

    private String getTbIdx(){
        return DBContextHolder.getTBKey();
    }
}
