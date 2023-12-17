package com.lilanfeng2089.study.classstudy;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ComputerBook extends Book {

    //protected float getPrice(){} //无法覆盖Book中的getPrice() 返回类型float与int不兼容

    //protected int getPrice(int page){}  //编译报错，缺少返回语句

    //int getPrice(){} //编译报错 正在尝试分配更低的访问权限; 以前为protected

    @Override
    public int getPrice(){return 10;}

    public static void main(String[] args) {
        Book book = new ComputerBook();
        System.out.println(book.getPrice());
    }
}

class Book {
    protected int getPrice(){
        return 30;
    }
}
