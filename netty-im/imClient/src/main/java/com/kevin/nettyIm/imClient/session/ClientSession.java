package com.kevin.nettyIm.imClient.session;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.Channel;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description 实现客户端 Session会话
 * @date 2025/6/11 22:59
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Data
@Slf4j
public class ClientSession {

    public static final AttributeKey<ClientSession> SESSION_KEY = AttributeKey.valueOf("SESSION_KEY");

    private Channel channel;
    private User user;


    private String sessionId;

    private  boolean isConnected = false;
    private  boolean isLogin = false;

    /**
     * 构造函数
     * 绑定通道
     * 链接成功之后
      */
    public ClientSession(Channel channel) {
        this.channel = channel;
        this.user = user;
        this.sessionId = String.valueOf(-1);

        channel.attr(ClientSession.SESSION_KEY).set(this);
    }

    public static void loginSuccess(ChannelHandlerContext ctx,ProtoMsg.Message pkg){
        Channel channel = ctx.channel();
        ClientSession session = channel.attr(ClientSession.SESSION_KEY).get();
        session.setSessionId(pkg.getSessionId());
        session.setLogin(true);
        log.info("登录成功：{}",pkg.getSessionId());
    }

    public static void loginFailed(ChannelHandlerContext ctx,ProtoMsg.Message pkg){
        Channel channel = ctx.channel();
        ClientSession session = channel.attr(ClientSession.SESSION_KEY).get();
        session.setSessionId(pkg.getSessionId());
        session.setLogin(false);
        log.info("登录失败：{}",pkg.getSessionId());
    }

}
