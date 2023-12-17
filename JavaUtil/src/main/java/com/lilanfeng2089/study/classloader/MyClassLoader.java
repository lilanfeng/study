package com.lilanfeng2089.study.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * todo 自定义加载器 完成打破双亲委派机制
 */
public class MyClassLoader extends ClassLoader {

    private String path;

    public MyClassLoader(String classPath) {
        this.path = classPath;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)){
            // 第一 检查是否已经加载了该name的class
            Class<?> clazz = findLoadedClass(name);
            if(clazz != null){
                return clazz;
            }

            //clazz = this.getParent().loadClass(name,false);
            if (!name.startsWith("java.")) {
                try {
                    byte[]  classData = getclassData(name);
                    if(classData != null){
                        return defineClass(name,classData,0,classData.length);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.loadClass(name, resolve);
    }

    private byte[] getclassData(String className) throws IOException {
        String classPath = path + File.separator + className.replaceAll("\\.",File.separator) + ".class";
        try (InputStream inputStream = new FileInputStream(classPath);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,bytesRead);
            }
            return outputStream.toByteArray();

        }
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String classPath = "/Users/lilanfeng/workspace/gitee/study/JavaUtil/src/main/java/";
        MyClassLoader myClassLoader = new MyClassLoader(classPath);
        try {
            //尝试加载Java核心类库中的类
            Class<?> systemClass = myClassLoader.loadClass("java.lang.String",false);
            System.out.println("系统核心类库中的类加载成功"+systemClass.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("系统核心类库中的类加载失败:"+e.getMessage());
        }

        try {
            //尝试加载自定义类
            Class<?> myClass = myClassLoader.loadClass("com.lilanfeng2089.study.collection.map.MapLoopCompare",false);
            System.out.println("系统核心类库中的类加载成功"+myClass.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("系统核心类库中的类加载失败:"+e.getMessage());
        }

    }
}
