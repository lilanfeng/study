package com.lilanfeng2089.mybatis.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @program: com.lilanfeng2089.mybatis.io
 * @description: 通过类加载器获得resource的辅助类
 * @author: lilf@bwoil.com
 * @create: 2024-06-03 17:12
 **/
public class Resources {

    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    /**
     * 获取资源
     * @param resource
     * @return
     */
    private static InputStream getResourceAsStream(String resource) {
        ClassLoader[] classLoaders = getClassLoaders();
        return Resources.class.getClassLoader().getResourceAsStream(resource);
    }

    /**
     *  获取ClassLoader
     * @return
     */
    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()
        };
    }

    /**
     * 通过类名获得Class
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> classForName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

}
