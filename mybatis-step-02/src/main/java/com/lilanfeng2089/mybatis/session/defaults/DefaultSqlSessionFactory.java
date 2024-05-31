package com.lilanfeng2089.mybatis.session.defaults;

import com.lilanfeng2089.mybatis.binding.MapperRegistry;
import com.lilanfeng2089.mybatis.session.SqlSession;
import com.lilanfeng2089.mybatis.session.SqlSessionFactory;

/**
 * @program: com.lilanfeng2089.mybatis.session.defaults
 * @description: 默认sqlSession工厂
 * @author: lilf@bwoil.com
 * @create: 2024-05-31 14:38
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
