CREATE TABLE chatmessage
(
    message_ID          bigint          NOT NULL    AUTO_INCREMENT,
    sender_email_fk      varchar(50)     NOT NULL,
    receiver_email_fk    varchar(50)     NOT NULL,
    message             varchar(500)    NOT NULL,
    message_date        varchar(500)    NOT NULL,
    message_status      varchar(500)    NOT NULL,
    PRIMARY KEY (message_ID),
    CONSTRAINT FK_ChatMessage_Sender FOREIGN KEY (sender_email_fk) REFERENCES user(email),
    CONSTRAINT FK_ChatMessage_Receiver FOREIGN KEY (receiver_email_fk) REFERENCES user(email)
);