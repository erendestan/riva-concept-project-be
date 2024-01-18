package com.example.rivaconceptproject.controller;

import com.example.rivaconceptproject.business.chatmessageusecase.GetChatMessagesUseCase;
import com.example.rivaconceptproject.domain.chat.ChatMessage;
import com.example.rivaconceptproject.domain.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ChatHistoryControllerTest {
    @Mock
    private GetChatMessagesUseCase getChatMessagesUseCase;

    @InjectMocks
    private ChatHistoryController chatHistoryController;

    @Test
    void getChatHistory_shouldReturnChatMessages() throws Exception {
        // Create test data
        List<ChatMessage> chatMessages = Arrays.asList(
                new ChatMessage("sender@example.com", "receiver@example.com", "Hello", "2022-01-18T12:00:00", Status.MESSAGE),
                new ChatMessage("receiver@example.com", "sender@example.com", "Hi", "2022-01-18T13:00:00", Status.MESSAGE)
        );

        // Mock service behavior
        when(getChatMessagesUseCase.getChatMessages("user@example.com")).thenReturn(chatMessages);

        // Set up MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(chatHistoryController).build();

        // Perform GET request
        mockMvc.perform(get("/chathistory/email/{email}", "user@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].senderEmail").value("sender@example.com"))
                .andExpect(jsonPath("$[0].receiverEmail").value("receiver@example.com"))
                .andExpect(jsonPath("$[0].message").value("Hello"))
                .andExpect(jsonPath("$[1].senderEmail").value("receiver@example.com"))
                .andExpect(jsonPath("$[1].receiverEmail").value("sender@example.com"))
                .andExpect(jsonPath("$[1].message").value("Hi"));
    }
}
