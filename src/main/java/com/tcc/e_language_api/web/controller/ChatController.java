package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.entity.Chat;
import com.tcc.e_language_api.jwt.JwtUserDetails;
import com.tcc.e_language_api.service.ChatService;
import com.tcc.e_language_api.web.dto.ChatDto;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import com.tcc.e_language_api.web.dto.mapper.ChatMapper;
import com.tcc.e_language_api.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<?> update(@RequestBody @Valid ChatDto dto, @AuthenticationPrincipal JwtUserDetails userDetails, HttpServletRequest request){
        try {
            chatService.create(ChatMapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(request, HttpStatus.OK, "Chat criado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @GetMapping("/{alunoProfessorId}/aluno-professor")
    public ResponseEntity<?> update(@PathVariable UUID alunoProfessorId, HttpServletRequest request){
        try {
            List<Chat> chats = chatService.getByAlunoProfessor(alunoProfessorId);
            return ResponseEntity.status(HttpStatus.OK).body( ChatMapper.toListDto(chats));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}
