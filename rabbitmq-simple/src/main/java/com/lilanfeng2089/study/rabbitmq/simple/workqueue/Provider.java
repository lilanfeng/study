package com.lilanfeng2089.study.rabbitmq.simple.workqueue;

import com.lilanfeng2089.study.rabbitmq.simple.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @program: com.lilanfeng2089.study.rabbitmq.simple.workqueue
 * @description: 任务模型。当消息处理比较耗时的时候，可能生产消息的速度会远远大于消息的消费速度。
 * 长此以往，消息就会堆积越来越多，无法及时处理。此时就可以使用work模型：让多个消费者绑定到一个队列，
 * 共同消费队列中的消息。队列中的消息一旦消费，就会消失，因此任务是不会被重复执行的
 * @author: lilf@bwoil.com
 * @create: 2024-03-21 17:16
 **/
public class Provider {

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

        for (int i = 1; i <= 200; i++){
            //生产消息
            String message = "hello work queue-" + i;
            //参数1：交换机名称
            //参数2：队列名称
            //参数3：传递消息额外设置
            //参数4：消息的具体内容
            channel.basicPublish("","work",null,message.getBytes());
        }
        //关闭
        RabbitMQUtils.closeChanelAndConnecton(channel,connection);

    }
}
