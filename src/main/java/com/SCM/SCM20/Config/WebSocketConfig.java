package com.SCM.SCM20.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


//step1. Configuring websocket endpoints and message broker
@Configuration
@EnableWebSocketMessageBroker //Enables WebSocket message handling, backed by a message broker, for real-time communication.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    //Defines the WebSocket endpoint where clients will connect.
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").withSockJS();
        // /websocket: The URL endpoint for WebSocket connections.
        // withSockJS(): Enables SockJS fallback options for browsers that do not support native WebSockets.

        //SockJS ensures that clients can still establish a connection and communicate with the server,
        // even in environments where WebSockets cannot be used.

        /*
        *   Fallback Mechanism
            SockJS provides alternative transport mechanisms that mimic WebSocket-like behavior when native WebSocket
            connections fail. These include:
            XHR Polling: Repeatedly makes HTTP requests to fetch updates.
            XHR Streaming: Streams updates over a long-lived HTTP request.
            Iframe Transport: Uses a hidden iframe for communication.
            EventSource: Uses server-sent events for communication (if supported).
        *
        *
        *   Why It's Important
            Using withSockJS() ensures that your WebSocket-based application can:

            Work on a wide range of browsers and network conditions.
            Provide a consistent user experience without requiring users to upgrade their browser or change network settings.
        *
        * */
    }

    @Override
    //Configures the message broker that handles routing messages between clients.
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");

        //configureMessageBroker(MessageBrokerRegistry registry)
        //Configures the message broker that handles routing messages between clients.

        //enableSimpleBroker("/topic"): Sets up an in-memory message broker for broadcasting messages to subscribed
        //clients. Messages sent to destinations starting with /topic will be routed here.

        //setApplicationDestinationPrefixes("/app"): Prefix for application-level messages.
        // Messages sent from clients to destinations starting with /app are routed to message-handling methods
        // (e.g., annotated with @MessageMapping)
    }

}

//Purpose
//This setup allows the application to:
//Enable WebSocket communication with fallback support (SockJS).
//Route client messages to server-side message handlers.
//Broadcast messages to all subscribed clients using a simple broker.



//Brief
//registerStompEndpoints method is used to register a websocket endpoint that the clients will use to connect to the server.
//configureMessageBroker method is used to configure our message broker which will be used to route messages from one client to another.