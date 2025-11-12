package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.entity.Chat;
import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.web.dto.ChatDto;

import java.util.List;
import java.util.stream.Collectors;

public class ChatMapper {
    public static Chat toEntity(ChatDto dto) {
        AlunoProfessor alunoProfessor = new AlunoProfessor();
        alunoProfessor.setAlunoProfessorId(dto.getAlunoProfessorId());

        Perfil remetente = new Perfil();
        remetente.setPerfilId(dto.getRemetenteId());

        Chat chat = new Chat();
        chat.setAlunoProfessor(alunoProfessor);
        chat.setRemetente(remetente);
        chat.setMensagem(dto.getMensagem());
        chat.setDataEnvio(dto.getDataEnvio());

        return chat;
    }

    public static ChatDto toDto(Chat chat) {
        ChatDto dto = new ChatDto();
        dto.setChatId(chat.getChatId());
        dto.setAlunoProfessorId(chat.getAlunoProfessor().getAlunoProfessorId());
        dto.setRemetenteId(chat.getRemetente().getPerfilId());
        dto.setMensagem(chat.getMensagem());
        dto.setDataEnvio(chat.getDataEnvio());
        return dto;
    }

    public static List<ChatDto> toListDto(List<Chat> chats){
        return chats.stream().map(chat -> toDto(chat)).collect(Collectors.toList());
    }
}
