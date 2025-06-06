package com.ema.ema_backend.domain.message.service;

import com.ema.ema_backend.domain.chatroom.ChatRoom;
import com.ema.ema_backend.domain.message.Message;
import com.ema.ema_backend.domain.message.SenderType;
import com.ema.ema_backend.domain.message.dto.MessageSet;
import com.ema.ema_backend.domain.message.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    @Transactional
    public List<MessageSet> getMessageSet(List<Long> messageIdList){
        return messageRepository.findAllById(messageIdList).stream().map(MessageSet::from).collect(Collectors.toList());
    }

    @Transactional
    public Message createMessage(String role, String content, ChatRoom chatRoom){
        Message message = new Message(role, content, chatRoom);
        return messageRepository.save(message);
    }
}
