package com.example.rivaconceptproject.controller;

import com.example.rivaconceptproject.business.chatmessageusecase.GetChatMessagesUseCase;
import com.example.rivaconceptproject.domain.chat.ChatMessage;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/chathistory")
@RestController
public class ChatHistoryController {
    private final GetChatMessagesUseCase getChatMessagesUseCase;

    //Get Mapping for Chat History
    @RolesAllowed({"CUSTOMER", "ADMIN"})
    @GetMapping("/email/{email}")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@PathVariable(value = "email") final String email){
        final List<ChatMessage> chatMessages = getChatMessagesUseCase.getChatMessages(email);

        return ResponseEntity.ok().body(chatMessages);
    }
}
