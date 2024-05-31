package com.lilanfeng2089.mybatis.test.dao;

/**
 * @program: com.lilanfeng2089.mybatis.test.dao
 * @description: 用户操作数据DAO
 * @author: lilf@bwoil.com
 * @create: 2024-05-30 16:52
 **/
public interface IUserDao {
    String queryUserName(String uId);

    Integer queryUserAge(String uId);

}
