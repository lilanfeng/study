package com.lilanfeng2089.study.therad;

public class InterruptThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        MyThread m1 = new MyThread();
        System.out.println("Starting thread...");
        m1.start();
        Thread.sleep(3000);
        System.out.println("Interrupt thread ...:" + m1.getName());
        //m1.stop = true;
        m1.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");

    }
}

class MyThread extends Thread{
    volatile boolean stop = false;

    @Override
    public void run(){
        while(!stop){
            System.out.println(getName() + " is running");
            try {
                sleep(1000);
            }catch (InterruptedException e){
                System.out.println("week up from blcok...");
                //stop = true;
            }
        }
        System.out.println(getName() + " is exiting...");
    }

}
