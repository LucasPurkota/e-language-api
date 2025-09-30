package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.repository.IdiomaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(2) // Executa depois do TipoPerfilInitializer
public class IdiomaInitializer implements CommandLineRunner {
    
    private final IdiomaRepository idiomaRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeIdiomas();
    }

    private void initializeIdiomas() {
        try {
            // Verificar se já existem idiomas
            if (idiomaRepository.count() == 0) {
                log.info("Inicializando idiomas...");
                
                // Criar idioma Inglês
                Idioma ingles = new Idioma();
                ingles.setNome("Inglês");
                ingles.setSigla("EN");
                idiomaRepository.save(ingles);
                
                // Criar idioma Espanhol
                Idioma espanhol = new Idioma();
                espanhol.setNome("Espanhol");
                espanhol.setSigla("ES");
                idiomaRepository.save(espanhol);
                
                log.info("Idiomas criados: Inglês, Espanhol");
            } else {
                log.info("Idiomas já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar idiomas: {}", e.getMessage());
        }
    }
}