package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Chat;
import com.tcc.e_language_api.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Transactional
    public void create(Chat chat) {
        chatRepository.save(chat);
    }

    @Transactional
    public List<Chat> getByAlunoProfessor(UUID alunoProfessorId) {
        return chatRepository.findByAlunoProfessorAlunoProfessorId(alunoProfessorId);
    }
}
