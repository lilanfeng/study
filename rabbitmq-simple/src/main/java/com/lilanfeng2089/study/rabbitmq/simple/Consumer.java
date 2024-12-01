package com.lilanfeng2089.study.rabbitmq.simple;

import com.lilanfeng2089.study.rabbitmq.simple.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: com.lilanfeng2089.study.rabbitmq.simple
 * @description: 消费者 consumer
 * @author: lilf@bwoil.com
 * @create: 2024-03-13 11:16
 **/
public class Consumer {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道
        Channel channel =  connection.createChannel();

        //通道绑定对应的消息队列
        //参数1： 队列名称，如果队列不存在会自动创建
        //参数2： 用来定义队列特性是否要持久化
        //参数3： 是否独占队列，表示只有当前连接可用该队列
        //参数4： 是否在消费完成后删除队列
        //参数5： 额外附加参数
        channel.queueDeclare("hello",false,false,false,null);

        //消费消息
        //参数1： 消费哪个队列的消息
        //参数2： 开启消息的自动确认机制
        //参数3： 消费时回调的接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,byte[] body) throws IOException {

                super.handleDelivery(consumerTag,envelope,properties,body);
                System.out.println("消费者消费的内容是-->" + new String(body));
            }

        });

        //如果没关闭，消费者会一直等待下去
        //如果关闭，不会等待结果出来
        //RabbitMQUtils.closeChanelAndConnecton(channel,RabbitMQUtils.getConnection());

    }
}
