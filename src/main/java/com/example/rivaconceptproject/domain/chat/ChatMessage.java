package com.example.rivaconceptproject.domain.chat;

import com.example.rivaconceptproject.domain.enums.Status;
import lombok.*;

//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
