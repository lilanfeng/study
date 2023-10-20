package com.lilanfeng2089.study.netty.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ClientDemo {

    public static void main(String[] args) throws IOException {

        while (true){

            //1,创建Socket对象
            Socket socket = new Socket("127.0.0.1",9999);
            //2,从连接中取出输出流并发消息
            OutputStream os = socket.getOutputStream();
            System.out.println("请输入：");
            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();
            os.write(msg.getBytes(StandardCharsets.UTF_8));
            //3，从连接中取出输入流并接收回话
            InputStream is = socket.getInputStream();
            byte[] b = new byte[1024];
            int read = is.read(b);
            System.out.println("老板说:" + new String(b,0,read));
            //4，关闭
            socket.close();
        }
    }
}
