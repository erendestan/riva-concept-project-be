package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.domain.chat.ChatMessage;

import java.util.List;

public interface GetChatMessagesUseCase {
    List<ChatMessage> getChatMessages(String email);
}
