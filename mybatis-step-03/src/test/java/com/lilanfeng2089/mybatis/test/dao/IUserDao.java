package com.lilanfeng2089.mybatis.test.dao;

import com.lilanfeng2089.mybatis.test.po.User;

/**
 * @program: com.lilanfeng2089.mybatis.test.dao
 * @description: 用户操作数据DAO
 * @author: lilf@bwoil.com
 * @create: 2024-05-30 16:52
 **/
public interface IUserDao {
    User queryUserInfoById(String uId);


}
