package com.example.rivaconceptproject.business.impl.ChatMessageUseCaseImplTests;

import com.example.rivaconceptproject.business.exception.ErrorSavingMessageException;
import com.example.rivaconceptproject.business.impl.chatmessageusecaseimpls.SaveChatMessageUseCaseImpl;
import com.example.rivaconceptproject.domain.chat.ChatMessage;
import com.example.rivaconceptproject.domain.enums.Status;
import com.example.rivaconceptproject.persistence.ChatMessageRepository;
import com.example.rivaconceptproject.persistence.UserRepository;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveChatMessagesUseCaseImplTest {
    @Mock
    private ChatMessageRepository chatMessageRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private SaveChatMessageUseCaseImpl saveChatMessageUseCase;

    @Test
    void saveChatMessage_senderNotFound_shouldThrowException() {
        // Create test data
        String dateString = "2022-01-18T12:00:00";

        ChatMessage chatMessage = ChatMessage.builder()
                .senderEmail("nonexistent@example.com")
                .receiverEmail("receiver@example.com")
                .message("Hello!")
                .date(dateString)
                .status(Status.MESSAGE)
                .build();

        // Mock repository behavior
        when(userRepositoryMock.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Execute the use case and verify the exception
        assertThrows(ErrorSavingMessageException.class, () ->
                saveChatMessageUseCase.saveChatMessage(chatMessage));

        // Verify the behavior
        verify(userRepositoryMock).findByEmail("nonexistent@example.com");
        verifyNoMoreInteractions(chatMessageRepositoryMock);
    }

    @Test
    void saveChatMessage_receiverNotFound_shouldThrowException() {
        // Create test data
        String dateString = "2022-01-18T12:00:00";

        ChatMessage chatMessage = ChatMessage.builder()
                .senderEmail("sender@example.com")
                .receiverEmail("nonexistent@example.com")
                .message("Hello!")
                .date(dateString)
                .status(Status.MESSAGE)
                .build();

        // Mock repository behavior
        when(userRepositoryMock.findByEmail("sender@example.com")).thenReturn(Optional.of(new UserEntity()));
        when(userRepositoryMock.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Execute the use case and verify the exception
        assertThrows(ErrorSavingMessageException.class, () ->
                saveChatMessageUseCase.saveChatMessage(chatMessage));

        // Verify the behavior
        verify(userRepositoryMock).findByEmail("sender@example.com");
        verify(userRepositoryMock).findByEmail("nonexistent@example.com");
        verifyNoMoreInteractions(chatMessageRepositoryMock);
    }
}
