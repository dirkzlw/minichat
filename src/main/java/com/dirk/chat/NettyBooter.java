package com.dirk.chat;

import com.dirk.chat.netty.WebSocketServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Dirk
 * @date 2020-06-07 11:38
 * @description 监听容器启动成功后启动netty server
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null){
            try {
                WebSocketServer.getInstance().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
