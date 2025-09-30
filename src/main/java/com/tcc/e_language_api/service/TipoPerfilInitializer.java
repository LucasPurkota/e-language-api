package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.TipoPerfil;
import com.tcc.e_language_api.repository.TipoPerfilRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
                                                                                                                                                                                                                                                                            import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(1) // Executa primeiro
public class TipoPerfilInitializer implements CommandLineRunner {
    
    private final TipoPerfilRepository tipoPerfilRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeTiposPerfil();
    }

    private void initializeTiposPerfil() {
        try {
            if (tipoPerfilRepository.count() == 0) {
                log.info("Inicializando tipos de perfil...");
                
                // Criar tipo ALUNO
                TipoPerfil aluno = new TipoPerfil();
                aluno.setTipoPerfilId(1);
                aluno.setDescricao("ALUNO");                                                                                    
                tipoPerfilRepository.save(aluno);                               

                // Criar tipo PROFESSOR
                TipoPerfil professor = new TipoPerfil();
                professor.setTipoPerfilId(2);
                professor.setDescricao("PROFESSOR");
                tipoPerfilRepository.save(professor);
                
                log.info("Tipos de perfil criados: 1=ALUNO, 2=PROFESSOR");
            } else {
                log.info("Tipos de perfil já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar tipos de perfil: {}", e.getMessage());
        }
    }
}