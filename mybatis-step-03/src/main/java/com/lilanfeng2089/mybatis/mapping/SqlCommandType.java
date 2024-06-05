package com.lilanfeng2089.mybatis.mapping;

/**
 * @program: com.lilanfeng2089.mybatis.mapping
 * @description:
 * @author: lilf@bwoil.com
 * @create: 2024-06-03 17:13
 **/
public enum SqlCommandType {
    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查询
     */
    SELECT,
}
