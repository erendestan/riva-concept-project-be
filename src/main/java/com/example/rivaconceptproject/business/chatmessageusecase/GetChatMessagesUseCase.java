package com.example.rivaconceptproject.business.chatmessageusecase;

import com.example.rivaconceptproject.domain.chat.ChatMessage;

import java.util.List;

public interface GetChatMessagesUseCase {
    List<ChatMessage> getChatMessages(String email);
}
