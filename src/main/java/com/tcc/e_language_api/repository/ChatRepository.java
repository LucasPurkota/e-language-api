package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Chat;
import com.tcc.e_language_api.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    @Query(value = "SELECT chat.* FROM chat " +
            "WHERE chat.aluno_professor_id = :alunoProfessorId",
            nativeQuery = true)
    List<Chat> findByAlunoProfessorAlunoProfessorId(@Param("alunoProfessorId") UUID alunoProfessorId);
}
