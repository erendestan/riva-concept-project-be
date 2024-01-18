package com.example.rivaconceptproject.persistence;

import com.example.rivaconceptproject.persistence.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    @Query("SELECT cm FROM ChatMessageEntity cm WHERE cm.senderEmail.email = :email OR cm.receiverEmail.email = :email")
    List<ChatMessageEntity> findBySenderOrReceiverEmail(@Param("email") String email);
}
