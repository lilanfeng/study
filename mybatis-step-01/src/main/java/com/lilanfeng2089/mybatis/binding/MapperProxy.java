package com.lilanfeng2089.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @program: com.lilanfeng2089.mybatis.binding
 * @description: 映射器代理类
 * @author: lilf@bwoil.com
 * @create: 2024-05-30 16:31
 **/
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private Map<String,String> sqlSession;

    private final Class<T> mapperInterface;

    public MapperProxy(Map<String,String> sqlSession,Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return "您的被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }
}
