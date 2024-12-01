package com.lilanfeng2089.mybatis.test;

import com.lilanfeng2089.mybatis.binding.MapperRegistry;
import com.lilanfeng2089.mybatis.session.SqlSession;
import com.lilanfeng2089.mybatis.session.SqlSessionFactory;
import com.lilanfeng2089.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.lilanfeng2089.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: com.lilanfeng2089.mybatis.test
 * @description: 测试模块
 * @author: lilf@bwoil.com
 * @create: 2024-05-31 14:41
 **/
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);


    @Test
    public void test_MapperProxyFactory() {
        //1,注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.lilanfeng2089.mybatis.test.dao");

        //2, 从SqlSession 工厂获取Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3, 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        //4, 测试验证
        String res = userDao.queryUserName("10001");

        logger.info("测试结果：{}", res);

    }
}
