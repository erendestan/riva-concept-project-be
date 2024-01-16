package com.example.rivaconceptproject.persistence.entity;

import com.example.rivaconceptproject.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "chatmessage")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sender_email_fk", referencedColumnName = "email")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity senderEmail;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "receiver_email_fk", referencedColumnName = "email")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity receiverEmail;

    @NotBlank
    @Length(max = 500)
    @Column(name = "message")
    private String message;

    @NotBlank
    @Length(max = 500)
    @Column(name = "message_date")
    private String date;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "message_status", length = 500)
    private Status status;
}


