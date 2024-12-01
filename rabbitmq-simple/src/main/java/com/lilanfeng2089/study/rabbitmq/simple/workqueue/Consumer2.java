package com.lilanfeng2089.study.rabbitmq.simple.workqueue;

import com.lilanfeng2089.study.rabbitmq.simple.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: com.lilanfeng2089.study.rabbitmq.simple.workqueue
 * @description: 消费者2
 * @author: lilf@bwoil.com
 * @create: 2024-03-21 17:59
 **/
public class Consumer2 {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //通过通道声明队列
        //参数1：队列名称，如果队列不存在会自动创建
        //参数2：用来定义队列特性是否要持久化
        //参数3：是否独占队列，表示只有当前连接可用该队列
        //参数4：是否在消费完成后删除队列
        //参数5：额外附件参数
        channel.queueDeclare("work",true,false,false,null);

        //消费消息
        channel.basicConsume("work",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)throws IOException{
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("消费者2---"+ new String(body));
            }

        });

    }
}
