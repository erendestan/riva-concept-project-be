package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.domain.chat.ChatMessage;
import com.example.rivaconceptproject.persistence.entity.ChatMessageEntity;

public class ChatMessageConverter {
    private ChatMessageConverter(){
        // private constructor to hide the implicit public one
    }

    public static ChatMessage convert(ChatMessageEntity chatMessage){
        return ChatMessage.builder()
                .senderEmail(chatMessage.getSenderEmail().getEmail())
                .receiverEmail(chatMessage.getReceiverEmail().getEmail())
                .message(chatMessage.getMessage())
                .date(chatMessage.getDate())
                .status(chatMessage.getStatus())
                .build();
    }
}
