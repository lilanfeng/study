package com.lilanfeng2089.study.io.randomaccess;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
 public class A {

	public A(){
            System.out.println("A");
        }
    }

    class B extends Thread{

        public static void main(String[] args){
            B  b = new B();
            b.run();
        }

//        public B(){
//            for(int i = 0; i < 10; i++){
//                System.out.println(i);
//            }
//        }
        @Override
        public void start(){
            for(int i= 0; i < 10; i++){
                System.out.println("Value of I = " + i);
            }
        }




    }


