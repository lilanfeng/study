package com.lilanfeng2089.mybatis.session;

import com.lilanfeng2089.mybatis.binding.MapperRegistry;
import com.lilanfeng2089.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: com.lilanfeng2089.mybatis.session
 * @description: 配置项
 * @author: lilf@bwoil.com
 * @create: 2024-06-03 17:13
 **/
public class Configuration {

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappers(String packageName){
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type){
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms){
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id){
        return mappedStatements.get(id);
    }



}
