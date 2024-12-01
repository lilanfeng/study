package com.lilanfeng2089.mybatis.session;

import com.lilanfeng2089.mybatis.builder.xml.XMLConfigBuilder;
import com.lilanfeng2089.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @program: com.lilanfeng2089.mybatis.session
 * @description: 构建SqlSessionFactory的工厂
 * @author: lilf@bwoil.com
 * @create: 2024-06-03 17:14
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);

        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration)
    {
        return new DefaultSqlSessionFactory(configuration);
    }
}
