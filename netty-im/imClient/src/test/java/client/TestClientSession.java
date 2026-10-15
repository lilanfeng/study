package client;

import com.kevin.nettyIm.imClient.session.ClientSession;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.Channel;
import io.netty.util.Attribute;
import java.util.logging.Logger;
import org.junit.Test;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2025/6/11 07:18
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Slf4j
public class TestClientSession {

    /**
     * 测试用例， 会话的双向绑定
     */
    @Test
    public void testSessionBind() throws Exception{
        ClientSession  session = new ClientSession(new EmbeddedChannel());
        session.setUser(new User());

        Logger.cfo("eg:正向导航");
        Channel  channel = session.getChannel();

        Logger.cfo("eg:反向导航");
        Attribute<ClientSession> r_session = channel.attr(ClientSession.SESSION_KEY);
        Logger.cfo(JsonUtl.pojoToJson(r_session.get().getUser()));


    }




}
