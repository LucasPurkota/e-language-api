package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.NivelIdioma;
import com.tcc.e_language_api.repository.NivelIdiomaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(2) // Executa após TipoPerfilInitializer
public class NivelIdiomaInitializer implements CommandLineRunner {
    
    private final NivelIdiomaRepository nivelIdiomaRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeNiveisIdioma();
    }

    private void initializeNiveisIdioma() {
        try {
            if (nivelIdiomaRepository.count() == 0) {
                log.info("Inicializando níveis de idioma...");
                
                // Criar nível BÁSICO
                NivelIdioma basico = new NivelIdioma();
                basico.setNivelIdiomaId(1);
                basico.setDescricao("Iniciante");
                nivelIdiomaRepository.save(basico);

                // Criar nível INTERMEDIÁRIO
                NivelIdioma intermediario = new NivelIdioma();
                intermediario.setNivelIdiomaId(2);
                intermediario.setDescricao("Intermediário");
                nivelIdiomaRepository.save(intermediario);

                // Criar nível AVANÇADO
                NivelIdioma avancado = new NivelIdioma();
                avancado.setNivelIdiomaId(3);
                avancado.setDescricao("Avançado");
                nivelIdiomaRepository.save(avancado);

                log.info("Níveis de idioma criados: 1=BÁSICO, 2=INTERMEDIÁRIO, 3=AVANÇADO");
            } else {
                log.info("Níveis de idioma já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar níveis de idioma: {}", e.getMessage());
        }
    }
}