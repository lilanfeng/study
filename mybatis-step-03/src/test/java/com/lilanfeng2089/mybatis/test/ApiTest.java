package com.lilanfeng2089.mybatis.test;


import com.lilanfeng2089.mybatis.io.Resources;
import com.lilanfeng2089.mybatis.session.SqlSession;
import com.lilanfeng2089.mybatis.session.SqlSessionFactory;
import com.lilanfeng2089.mybatis.session.SqlSessionFactoryBuilder;
import com.lilanfeng2089.mybatis.test.dao.IUserDao;
import com.lilanfeng2089.mybatis.test.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.math.BigDecimal;

/**
 * @program: com.lilanfeng2089.mybatis.test
 * @description: 测试模块
 * @author: lilf@bwoil.com
 * @create: 2024-05-31 14:41
 **/
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);


    @Test
    public void test_SqlSessionFactory() throws Exception{
        //1,从SqlSessionFactory 中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2, 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        //3， 测试验证
        User user =  userDao.queryUserInfoById("10001");
        logger.info("测试结果：{}",user);

    }

    public static void main(String[] args) {
        if(0.1 + 0.2 == 0.3){
            System.out.println(0.1+0.2+"true");
        }else{
            System.out.println(0.1+0.2+"false");
        }

        if((0.1*10 + 0.2*10)/10 == 0.3){
            System.out.println((0.1*10 + 0.2*10)/10+"true");
        }else{
            System.out.println((0.1*10 + 0.2*10)/10+"false");
        }
        BigDecimal b1 = new BigDecimal("0.1"); //错误用法 存在精度损失的风险
        BigDecimal b2 = BigDecimal.valueOf(0.2);  //推荐用法
        BigDecimal d =  b1.add(b2);
        System.out.println(d);
    }
}
