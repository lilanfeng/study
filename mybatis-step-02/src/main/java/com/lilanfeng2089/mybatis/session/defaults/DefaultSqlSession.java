package com.lilanfeng2089.mybatis.session.defaults;

import com.lilanfeng2089.mybatis.binding.MapperRegistry;
import com.lilanfeng2089.mybatis.session.SqlSession;

/**
 * @program: com.lilanfeng2089.mybatis.session.defaults
 * @description: 默认sql会话
 * @author: lilf@bwoil.com
 * @create: 2024-05-31 14:38
 **/
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射注册机
     */
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type,this);
   }
}
