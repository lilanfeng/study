package com.lilanfeng2089.study.rabbitmq.simple;

import com.lilanfeng2089.study.rabbitmq.simple.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @program: com.lilanfeng2089.study.rabbitmq.simple
 * @description: 生产者Provider  第一种模型：直连
 * @author: lilf@bwoil.com
 * @create: 2024-03-12 19:50
 **/
public class Provider {

    @Test
    public void testSendMessage() throws IOException, InterruptedException {
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        // 通道绑定对应的消息队列
        // 参数1：队列名称，如果队列不存在会自动创建
        // 参数2：用来定义队列特性是否要持久化
        // 参数3：是否独占队列，表示只有当前连接可用该队列
        // 参数4：是否在消费完成后删除队列
        // 参数5：额外附加参数
        channel.queueDeclare("hello",false,false,false,null);

        for (int i = 0; i < 1000000000; i++) {
            String messge = "hello rabitmq number: " + i;
            channel.basicPublish("","hello",null,messge.getBytes());
            Thread.sleep(5);
        }

        //closed
        RabbitMQUtils.closeChanelAndConnecton(channel,RabbitMQUtils.getConnection());


    }
}
