package com.example.rivaconceptproject.domain.chat;

import com.example.rivaconceptproject.domain.enums.Status;
import lombok.*;

//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@ToString
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage {
    private String senderEmail;
    private String receiverEmail;
    private String message;
    private String date;
    private Status status;
}
