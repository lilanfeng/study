package cn.kevin.middleware.db.router;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description  数据源上下文
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DBContextHolder {

    private static final ThreadLocal<String> dbKey = new ThreadLocal<>();

    private static final ThreadLocal<String> tbKey = new ThreadLocal<>();

    public static void setDBKey(String dbKey) {
        DBContextHolder.dbKey.set(dbKey);
    }

    public static String getDBKey() {
        return dbKey.get();
    }

    public static void setTBKey(String tbKey) {
        DBContextHolder.tbKey.set(tbKey);
    }

    public static String getTBKey() {
        return tbKey.get();
    }

    public static void clearDBKey() {
        dbKey.remove();
    }

    public static void clearTBKey() {
        tbKey.remove();
    }
}
