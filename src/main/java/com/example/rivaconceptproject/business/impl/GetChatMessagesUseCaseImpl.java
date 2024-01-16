package com.example.rivaconceptproject.business.impl;

import com.example.rivaconceptproject.domain.chat.ChatMessage;
import com.example.rivaconceptproject.persistence.ChatMessageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetChatMessagesUseCaseImpl implements GetChatMessagesUseCase{
    private final ChatMessageRepository chatMessageRepository;
    @Transactional
    @Override
    public List<ChatMessage> getChatMessages(String email) {
        try{
            List<ChatMessage> chatMessages = chatMessageRepository.findBySenderOrReceiverEmail(email)
                    .stream()
                    .map(ChatMessageConverter::convert)
                    .toList();

            return chatMessages;
        }
        catch (Exception ex){
            throw (ex);
        }
    }
}
