package edu.hdu.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

    // 添加一个服务端点 /ws开头 ，来接收客户端的连接。
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    // 这个方法的作用是定义消息代理，设置消息连接请求的各种规范信息。
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端给服务端发消息的地址的前缀
        registry.setApplicationDestinationPrefixes("/app")
                //服务端给客户端发消息的地址的前缀信息
                .enableSimpleBroker("/topic");
    }

}
