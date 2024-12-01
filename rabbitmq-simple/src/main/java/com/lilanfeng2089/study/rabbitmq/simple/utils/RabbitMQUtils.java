package com.lilanfeng2089.study.rabbitmq.simple.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeoutException;

/**
 * @program: com.lilanfeng2089.study.rabbitmq.simple.utils
 * @description: RabbitMQ工具类
 * @author: lilf@bwoil.com
 * @create: 2024-03-12 18:00
 **/
public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;

    //类加载时只执行一次
    static {
        connectionFactory = new ConnectionFactory();
    }

    /**
     *
     * @return
     */
    public static Connection getConnection(){
        //1,连接MQ主机
        connectionFactory.setHost("localhost");
        //设置端口号
        connectionFactory.setPort(5672);

        //设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");

        try {
            return connectionFactory.newConnection();
        } catch (IOException | TimeoutException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void closeChanelAndConnecton(Channel channel,Connection connection){
        try {
            if(channel != null){
                channel.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (IOException | TimeoutException e){
            e.printStackTrace();
        }
    }


}
