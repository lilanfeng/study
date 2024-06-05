package com.lilanfeng2089.mybatis.session;

/**
 * @program: com.lilanfeng2089.mybatis.session
 * @description: sqlSession工厂
 * @author: lilf@bwoil.com
 * @create: 2024-05-31 14:39
 **/
public interface SqlSessionFactory {

    /**
     * 获取sqlSession
     * @return SqlSession
     */
    SqlSession openSession();
}
