package com.kevin.nettyIm.imClient.client;


import com.kevin.nettyIm.imClient.command.BaseCommand;
import com.kevin.nettyIm.imClient.command.ChatConsoleCommand;
import com.kevin.nettyIm.imClient.command.ClientCommandMenu;
import com.kevin.nettyIm.imClient.command.LoginConsoleCommand;
import com.kevin.nettyIm.imClient.command.LogoutConsoleCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2025/6/11 06:26
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Slef4j
@Data
@Service("CommandController")
public class CommandController {

    @Autowired
    ChatConsoleCommand  chatConsoleCommand;

    @Autowired
    LoginConsoleCommand loginConsoleCommand;

    @Autowired
    LogoutConsoleCommand logoutConsoleCommand;

    @Autowired
    ClientCommandMenu clientCommandMenu;

    private Map<String, BaseCommand> commandMap;


    public void initCommandMap() {
        commandMap = new HashMap<>();
        commandMap.put(clientCommandMenu.getKey(), clientCommandMenu);
        commandMap.put(chatConsoleCommand.getKey(), chatConsoleCommand);
        commandMap.put(ClientCommandMenu.LOGIN, loginConsoleCommand);
        commandMap.put(ClientCommandMenu.LOGOUT, logoutConsoleCommand);

        clientCommandMenu.setAllCommand(commandMap);
    }

    public void startConnectServer(){
        FutureTaskScheduler.add(()->{
            chatNettyClient.setConnectedListener(conectedListener);
            chatNettyClient.doConnect();
        });
    }
}
