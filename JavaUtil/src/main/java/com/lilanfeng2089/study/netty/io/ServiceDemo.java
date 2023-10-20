package com.lilanfeng2089.study.netty.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ServiceDemo {

    public static void main(String[] args) throws IOException {
        //1,创建一个线程池，如果有客户端连接就创建一个线程，与之通信
        ExecutorService executorService = Executors.newCachedThreadPool();
        //2,创建serviceSocket对象
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器已经启动");
        while (true){
            //3，监听客户端
            Socket socket = serverSocket.accept();  //阻塞的状态，等待有客户端连接
            System.out.println("有客户端连接");

            //4，开启新的线程处理
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket){
        try{

            System.out.println("线程ID：" + Thread.currentThread().getId() + "线程名称：" + Thread.currentThread().getName());
            //从连接中取出输入流来接收消息
            InputStream is = socket.getInputStream();
            byte[] b  = new byte[1024];
            int read = is.read(b);  //阻塞状态  同步下
            System.out.println("客户端："+ new String(b,0,read));
            //连接中取出输入流并回话
            OutputStream os = socket.getOutputStream();
            os.write("没钱".getBytes(StandardCharsets.UTF_8));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //关闭连接
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
