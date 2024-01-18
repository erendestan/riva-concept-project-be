package com.example.rivaconceptproject.business.chatmessageusecase;

import com.example.rivaconceptproject.domain.chat.ChatMessage;

public interface SaveChatMessageUseCase {
    void saveChatMessage(ChatMessage message);
}
