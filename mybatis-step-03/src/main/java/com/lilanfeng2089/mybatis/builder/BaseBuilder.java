package com.lilanfeng2089.mybatis.builder;

import com.lilanfeng2089.mybatis.session.Configuration;

/**
 * @program: com.lilanfeng2089.mybatis.builder
 * @description:
 * @author: lilf@bwoil.com
 * @create: 2024-06-03 17:12
 **/
public abstract  class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
