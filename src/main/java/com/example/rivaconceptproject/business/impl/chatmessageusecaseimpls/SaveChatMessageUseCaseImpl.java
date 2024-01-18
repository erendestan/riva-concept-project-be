package com.example.rivaconceptproject.business.impl.chatmessageusecaseimpls;

import com.example.rivaconceptproject.business.exception.ErrorSavingMessageException;
import com.example.rivaconceptproject.business.chatmessageusecase.SaveChatMessageUseCase;
import com.example.rivaconceptproject.domain.chat.ChatMessage;
import com.example.rivaconceptproject.persistence.ChatMessageRepository;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.ChatMessageEntity;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SaveChatMessageUseCaseImpl implements SaveChatMessageUseCase {

    private final ChatMessageRepository chatMessageRepository;

    private final UserRepository userRepository;
//    @Override
//    public void saveChatMessage(ChatMessage message) {
//        Optional<UserEntity> senderOptional = userRepository.findByEmail(message.getSenderEmail());
//        Optional<UserEntity> receiverOptional = userRepository.findByEmail(message.getReceiverEmail());
//
//        if (senderOptional.isPresent() && receiverOptional.isPresent()) {
//            ChatMessageEntity chatMessageEntity = getChatMessageEntity(message, senderOptional, receiverOptional);
//
//            // Save to the database
//            chatMessageRepository.save(chatMessageEntity);
//        } else {
//            // Handle case where user entities are not found (throw exception, log a message, etc.)
//        }
//    }

    @Override
    public void saveChatMessage(ChatMessage message) {
        Optional<UserEntity> senderOptional = userRepository.findByEmail(message.getSenderEmail());
        Optional<UserEntity> receiverOptional = userRepository.findByEmail(message.getReceiverEmail());

        if (senderOptional.isPresent() && receiverOptional.isPresent()) {
            senderOptional.ifPresent(sender -> receiverOptional.ifPresent(receiver -> {
                ChatMessageEntity chatMessageEntity = getChatMessageEntity(message, sender, receiver);

                // Save to the database
                chatMessageRepository.save(chatMessageEntity);
            }));
        } else {
            throw new ErrorSavingMessageException("Error saving message: Sender or receiver not found.");
        }
    }

    ChatMessageEntity getChatMessageEntity(ChatMessage message, UserEntity sender, UserEntity receiver) {
        // Create ChatMessage entity
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setSenderEmail(sender);
        chatMessageEntity.setReceiverEmail(receiver);
        chatMessageEntity.setMessage(message.getMessage());
        chatMessageEntity.setDate(message.getDate());
        chatMessageEntity.setStatus(message.getStatus());
        return chatMessageEntity;
    }

//     ChatMessageEntity getChatMessageEntity(ChatMessage message, Optional<UserEntity> senderOptional, Optional<UserEntity> receiverOptional) {
//        UserEntity sender = senderOptional.get();
//        UserEntity receiver = receiverOptional.get();
//
//        // Create ChatMessage entity
//        ChatMessageEntity chatMessageEntity =
//                new ChatMessageEntity();
//        chatMessageEntity.setSenderEmail(sender);
//        chatMessageEntity.setReceiverEmail(receiver);
//        chatMessageEntity.setMessage(message.getMessage());
//        chatMessageEntity.setDate(message.getDate());
//        chatMessageEntity.setStatus(message.getStatus());
//        return chatMessageEntity;
//    }
}
