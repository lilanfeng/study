package com.lilanfeng2089.mybatis.session.defaults;

import com.lilanfeng2089.mybatis.binding.MapperRegistry;
import com.lilanfeng2089.mybatis.mapping.MappedStatement;
import com.lilanfeng2089.mybatis.session.Configuration;
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
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement m = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter + "\n待执行SQL:"+m.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
   }

   @Override
   public Configuration getConfiguration() {
       return configuration;
   }
}
