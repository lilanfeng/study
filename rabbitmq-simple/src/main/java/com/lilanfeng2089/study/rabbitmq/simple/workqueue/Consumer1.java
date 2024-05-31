package com.lilanfeng2089.study.rabbitmq.simple.workqueue;

import com.lilanfeng2089.study.rabbitmq.simple.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: com.lilanfeng2089.study.rabbitmq.simple.workqueue
 * @description: 消费者1
 * @author: lilf@bwoil.com
 * @create: 2024-03-21 17:50
 **/
public class Consumer1 {
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
        //参数1： 消费哪个队列的消息
        //参数2： 开启消息的自动确认机制  false 需要手动确认   true 自动确认
        //参数3： 消费时回调的接口
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,byte[] body)throws IOException{
                System.out.println("消费者1---"+ new String(body));

                // 手动确认 参数1：手动确认消息标识  参数2：false每次确认一个
                channel.basicAck(envelope.getDeliveryTag(),false);

                //默认情况下，RabbitMQ会将每条消息按顺序发送给下一个使用者。 平均每个消费者将获得相同数量的消息。 这种分发消息的方式称为循环。
                //如果我们想要做到能者多劳，就要改变消息自动确认机制，改为手动确认消息。
            }

        });

    }
}
