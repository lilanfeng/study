package com.lilanfeng2089.study.therad;

/**
 *
 */
public class ThreadJoin {

    //现在有T1，T2，T3三个线程，你如何保证T2在T1执行完后执行，T3在T2执行完后执行
    public static void main(String[] args) throws InterruptedException {

        final Thread t1 = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("T1");
            }
        });

        final Thread t2 = new Thread(new Runnable(){
            @Override
            public void run(){
//                try{
//                    //引用t1线程，等待t1线程执行完
//                    t1.join();
//                } catch(InterruptedException e){
//                    e.printStackTrace();
//                }
                System.out.println("T2");
            }
        });

        final Thread t3 = new Thread(new Runnable(){
            @Override
            public void run(){
//                try{
//                    //引用t2线程，等待t1线程执行完
//                    t2.join();
//                } catch(InterruptedException e){
//                    e.printStackTrace();
//                }
                System.out.println("T3");
            }
        });
        //这里三个线程的启动顺序可以任意
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

    }



}
