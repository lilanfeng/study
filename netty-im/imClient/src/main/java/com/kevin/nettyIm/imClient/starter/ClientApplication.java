package com.kevin.nettyIm.imClient.starter;

import com.kevin.nettyIm.imClient.client.CommandController;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2025/6/11 07:02
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ClientApplication {


   /**
    * 启动聊天客户端
    * @param context
    */
   private static void startChatClient(ApplicationContext context) {

      CommandController  commandClient = context.getBean(CommandController.class);

      commandClient.initCommandMap();
      try {
         commandClient.commandThreadRunning();
      } catch (InterruptedException e){
         e.printStackTrace();
      }

   }
}
