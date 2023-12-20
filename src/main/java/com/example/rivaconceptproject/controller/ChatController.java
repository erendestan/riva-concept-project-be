package com.example.rivaconceptproject.controller;


import com.example.rivaconceptproject.domain.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") // /app/message
    @SendTo("/chatroom/public")
    private ChatMessage receivePublicMessage(@Payload ChatMessage message){
        return message;
    }

    @MessageMapping("/private-message")
    private ChatMessage receivePrivateMessage(@Payload ChatMessage message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message); // /user/Eren/private
        return message;
    }

}
