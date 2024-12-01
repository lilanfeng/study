package com.lilanfeng2089.mybatis.test.dao.com.lilanfeng2089.mybatis.test;

import com.lilanfeng2089.mybatis.binding.MapperProxyFactory;
import com.lilanfeng2089.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: com.lilanfeng2089.mybatis.test.dao.com.lilanfeng2089.mybatis.test
 * @description: 测试
 * @author: lilf@bwoil.com
 * @create: 2024-05-30 17:29
 **/
public class TestMapperProxyFactory {

    private final Logger logger = LoggerFactory.getLogger(TestMapperProxyFactory.class);

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String,String> sqlSession = new HashMap<>();

        sqlSession.put("com.lilanfeng2089.mybatis.test.dao.IUserDao.queryUserName","模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名select * from user where id = #{id}");
        sqlSession.put("com.lilanfeng2089.mybatis.test.dao.IUserDao.queryUserAge","模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄select * from user where id = #{id}");
        IUserDao userDao = factory.newInstance(sqlSession);

        String res = userDao.queryUserName("10001");
        logger.info("测试结果：{}",res);

    }
}
