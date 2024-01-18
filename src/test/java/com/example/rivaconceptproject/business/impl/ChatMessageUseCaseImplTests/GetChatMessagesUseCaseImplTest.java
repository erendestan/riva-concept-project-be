package com.example.rivaconceptproject.business.impl.ChatMessageUseCaseImplTests;

import com.example.rivaconceptproject.business.impl.chatmessageusecaseimpls.GetChatMessagesUseCaseImpl;
import com.example.rivaconceptproject.domain.chat.ChatMessage;
import com.example.rivaconceptproject.domain.enums.Role;
import com.example.rivaconceptproject.persistence.ChatMessageRepository;
import com.example.rivaconceptproject.persistence.entity.ChatMessageEntity;
import com.example.rivaconceptproject.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetChatMessagesUseCaseImplTest {
    @Mock
    private ChatMessageRepository chatMessageRepositoryMock;
    @InjectMocks
    private GetChatMessagesUseCaseImpl getChatMessagesUseCase;

    @Test
    void getChatMessages_shouldReturnChatMessages() {
        // Create test data
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password")
                .role(Role.CUSTOMER)
                .build();

        ChatMessageEntity chatMessageEntity1 = ChatMessageEntity.builder()
                .id(1L)
                .senderEmail(userEntity)
                .receiverEmail(userEntity)
                .message("Hello!")
                .build();

        ChatMessageEntity chatMessageEntity2 = ChatMessageEntity.builder()
                .id(2L)
                .senderEmail(userEntity)
                .receiverEmail(userEntity)
                .message("Hi there!")
                .build();

        List<ChatMessageEntity> chatMessageEntities = Arrays.asList(chatMessageEntity1, chatMessageEntity2);

        // Mock repository behavior
        when(chatMessageRepositoryMock.findBySenderOrReceiverEmail("john.doe@example.com"))
                .thenReturn(chatMessageEntities);

        // Execute the use case
        List<ChatMessage> result = getChatMessagesUseCase.getChatMessages("john.doe@example.com");

        // Verify the result
        assertEquals(chatMessageEntities.size(), result.size());

        // Additional verification
        verify(chatMessageRepositoryMock).findBySenderOrReceiverEmail("john.doe@example.com");
    }

    @Test
    void getChatMessages_repositoryException() {
        // Mock repository to throw an exception
        when(chatMessageRepositoryMock.findBySenderOrReceiverEmail("john.doe@example.com"))
                .thenThrow(new RuntimeException("Failed to retrieve chat messages"));

        // Execute the use case and verify the exception
        assertThrows(RuntimeException.class, () ->
                getChatMessagesUseCase.getChatMessages("john.doe@example.com"));

        // Additional verification
        verify(chatMessageRepositoryMock).findBySenderOrReceiverEmail("john.doe@example.com");
    }
}
