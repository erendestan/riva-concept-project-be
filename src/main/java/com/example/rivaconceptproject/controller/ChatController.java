package com.example.rivaconceptproject.controller;


import com.example.rivaconceptproject.business.chatmessageusecase.SaveChatMessageUseCase;
import com.example.rivaconceptproject.domain.chat.ChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final SaveChatMessageUseCase saveChatMessageUseCase;

    @MessageMapping("/private-message")
    private void receivePrivateMessage(@Payload ChatMessage message) {

        saveChatMessageUseCase.saveChatMessage(message);

        if ("designdocument@gmail.com".equals(message.getReceiverEmail())) {
            // Handle system messages separately
            simpMessagingTemplate.convertAndSendToUser("designdocument@gmail.com", "/private", message);
        } else {
            // Handle regular private messages
            simpMessagingTemplate.convertAndSendToUser(message.getReceiverEmail(), "/private", message);
        }
    }

}
