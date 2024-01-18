package com.example.rivaconceptproject.controller;


import com.example.rivaconceptproject.business.impl.userusecaseimpls.UserConverter;
import com.example.rivaconceptproject.business.userusecases.GetUserUseCase;
import com.example.rivaconceptproject.domain.chat.ChatMessage;
import com.example.rivaconceptproject.domain.user.User;
import com.example.rivaconceptproject.persistence.ChatMessageRepository;
import com.example.rivaconceptproject.persistence.entity.ChatMessageEntity;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@AllArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ChatMessageRepository chatMessageRepository;

    private final GetUserUseCase getUserUseCase;

    @MessageMapping("/private-message")
    private void receivePrivateMessage(@Payload ChatMessage message) {
        saveChatMessageToDatabase(message);

        if ("designdocument@gmail.com".equals(message.getReceiverEmail())) {
            // Handle system messages separately
            simpMessagingTemplate.convertAndSendToUser("designdocument@gmail.com", "/private", message);
        } else {
            // Handle regular private messages
            simpMessagingTemplate.convertAndSendToUser(message.getReceiverEmail(), "/private", message);
        }
    }


    // Helper method to save a ChatMessage to the database
    private void saveChatMessageToDatabase(ChatMessage message) {
        // Fetch UserEntity instances based on email addresses
        Optional<User> senderOptional = getUserUseCase.getUserByEmail(message.getSenderEmail());
        Optional<User> receiverOptional = getUserUseCase.getUserByEmail(message.getReceiverEmail());

        if (senderOptional.isPresent() && receiverOptional.isPresent()) {
            ChatMessageEntity chatMessageEntity = getChatMessageEntity(message, senderOptional, receiverOptional);

            // Save to the database
            chatMessageRepository.save(chatMessageEntity);
        } else {
            // Handle case where user entities are not found (throw exception, log a message, etc.)
        }
    }

    @NotNull
    private static ChatMessageEntity getChatMessageEntity(ChatMessage message, Optional<User> senderOptional, Optional<User> receiverOptional) {
        UserEntity sender = UserConverter.convertUserEntity(senderOptional.get());
        UserEntity receiver = UserConverter.convertUserEntity(receiverOptional.get());

        // Create ChatMessage entity
        ChatMessageEntity chatMessageEntity =
                new ChatMessageEntity();
        chatMessageEntity.setSenderEmail(sender);
        chatMessageEntity.setReceiverEmail(receiver);
        chatMessageEntity.setMessage(message.getMessage());
        chatMessageEntity.setDate(message.getDate());
        chatMessageEntity.setStatus(message.getStatus());
        return chatMessageEntity;
    }


}
