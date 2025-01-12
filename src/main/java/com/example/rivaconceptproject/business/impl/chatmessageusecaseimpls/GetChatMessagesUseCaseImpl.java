package com.example.rivaconceptproject.business.impl.chatmessageusecaseimpls;

import com.example.rivaconceptproject.business.chatmessageusecase.GetChatMessagesUseCase;
import com.example.rivaconceptproject.domain.chat.ChatMessage;
import com.example.rivaconceptproject.persistence.ChatMessageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetChatMessagesUseCaseImpl implements GetChatMessagesUseCase {
    private final ChatMessageRepository chatMessageRepository;
    @Transactional
    @Override
    public List<ChatMessage> getChatMessages(String email) {
        try {
            return chatMessageRepository.findBySenderOrReceiverEmail(email)
                    .stream()
                    .map(ChatMessageConverter::convert)
                    .toList();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
