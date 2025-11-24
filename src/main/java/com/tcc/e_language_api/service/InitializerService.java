package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(1)
public class InitializerService implements CommandLineRunner {
    private final FormaPagamentoRepository formaPagamentoRepository;
    private final NivelDificuldadeRepository nivelDificuldadeRepository;
    private final NivelIdiomaRepository nivelIdiomaRepository;
    private final StatusRepository statusRepository;
    private final TipoEnderecoRepository tipoEnderecoRepository;
    private final TipoPerfilRepository tipoPerfilRepository;
    private final TipoQuestaoRepository tipoQuestaoRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeFormasPagamento();
        initializeNiveisDificuldade();
        initializeNiveisIdioma();
        initializeStatus();
        initializeTiposEndereco();
        initializeTiposPerfil();
        initializeTiposQuestao();
    }

    private void initializeFormasPagamento() {
        try {
            if (formaPagamentoRepository.count() == 0) {
                log.info("Inicializando formas pagamento...");

                // Criar nível BÁSICO
                FormaPagamento boleto = new FormaPagamento();
                boleto.setFormaPagamentoId(1);
                boleto.setDescricao("Boleto");
                formaPagamentoRepository.save(boleto);

                // Criar nível INTERMEDIÁRIO
                FormaPagamento cartao = new FormaPagamento();
                cartao.setFormaPagamentoId(2);
                cartao.setDescricao("Cartão");
                formaPagamentoRepository.save(cartao);

                // Criar nível AVANÇADO
                FormaPagamento pix = new FormaPagamento();
                pix.setFormaPagamentoId(3);
                pix.setDescricao("Pix");
                formaPagamentoRepository.save(pix);

                log.info("Formas de pagamento criados: 1=Boleto, 2=Cartão, 3=Pix");
            } else {
                log.info("Formas de pagamento já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar formas de pagamento: {}", e.getMessage());
        }
    }

    private void initializeNiveisDificuldade() {
        try {
            if (nivelDificuldadeRepository.count() == 0) {
                log.info("Inicializando níveis de dificuldade...");

                // Criar nível BÁSICO
                NivelDificuldade facil = new NivelDificuldade();
                facil.setNivelDificuldadeId(1);
                facil.setDescricao("Fácil");
                nivelDificuldadeRepository.save(facil);

                // Criar nível INTERMEDIÁRIO
                NivelDificuldade medio = new NivelDificuldade();
                medio.setNivelDificuldadeId(2);
                medio.setDescricao("Médio");
                nivelDificuldadeRepository.save(medio);

                // Criar nível AVANÇADO
                NivelDificuldade dificil = new NivelDificuldade();
                dificil.setNivelDificuldadeId(3);
                dificil.setDescricao("Difícil");
                nivelDificuldadeRepository.save(dificil);

                log.info("Níveis de dificuldade criados: 1=Fácil, 2=Médio, 3=Difícil");
            } else {
                log.info("Níveis de dificuldade já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar dificuldade de idioma: {}", e.getMessage());
        }
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

                log.info("Níveis de idioma criados: 1=Iniciante, 2=Intermediário, 3=Avançado");
            } else {
                log.info("Níveis de idioma já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar níveis de idioma: {}", e.getMessage());
        }
    }

    private void initializeStatus() {
        try {
            if (statusRepository.count() == 0) {
                log.info("Inicializando Status...");

                // Criar nível BÁSICO
                Status pendente = new Status();
                pendente.setStatusId(1);
                pendente.setDescricao("Pendente");
                statusRepository.save(pendente);

                // Criar nível INTERMEDIÁRIO
                Status emAndamento = new Status();
                emAndamento.setStatusId(2);
                emAndamento.setDescricao("Em Andamento");
                statusRepository.save(emAndamento);

                // Criar nível AVANÇADO
                Status concluido = new Status();
                concluido.setStatusId(3);
                concluido.setDescricao("Concluido");
                statusRepository.save(concluido);

                log.info("Status criados: 1=Pendente, 2=Em Andamento, 3=Concluido");
            } else {
                log.info("Status já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar Status: {}", e.getMessage());
        }
    }

    private void initializeTiposEndereco() {
        try {
            if (tipoEnderecoRepository.count() == 0) {
                log.info("Inicializando Tipos Endereço...");

                // Criar nível BÁSICO
                TipoEndereco residencial = new TipoEndereco();
                residencial.setTipoEnderecoId(1);
                residencial.setDescricao("Residencial");
                tipoEnderecoRepository.save(residencial);

                // Criar nível INTERMEDIÁRIO
                TipoEndereco comercial = new TipoEndereco();
                comercial.setTipoEnderecoId(2);
                comercial.setDescricao("Comercial");
                tipoEnderecoRepository.save(comercial);

                log.info("Tipos Endereço criados: 1=Residencial, 2=Comercial");
            } else {
                log.info("Tipos Endereço já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar Tipos Endereço: {}", e.getMessage());
        }
    }

    private void initializeTiposPerfil() {
        try {
            if (tipoPerfilRepository.count() == 0) {
                log.info("Inicializando tipos de perfil...");

                // Criar tipo ALUNO
                TipoPerfil aluno = new TipoPerfil();
                aluno.setTipoPerfilId(1);
                aluno.setDescricao("Aluno");
                tipoPerfilRepository.save(aluno);

                // Criar tipo PROFESSOR
                TipoPerfil professor = new TipoPerfil();
                professor.setTipoPerfilId(2);
                professor.setDescricao("Professor");
                tipoPerfilRepository.save(professor);

                // Criar tipo ADMIN
                TipoPerfil admin = new TipoPerfil();
                admin.setTipoPerfilId(3);
                admin.setDescricao("Admin");
                tipoPerfilRepository.save(admin);

                log.info("Tipos de perfil criados: 1=Aluno, 2=Professor, 3=Admin");
            } else {
                log.info("Tipos de perfil já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar tipos de perfil: {}", e.getMessage());
        }
    }

    private void initializeTiposQuestao() {
        try {
            if (tipoQuestaoRepository.count() == 0) {
                log.info("Inicializando Tipo Questão...");

                // Criar nível BÁSICO
                TipoQuestao objetiva = new TipoQuestao();
                objetiva.setTipoQuestaoId(1);
                objetiva.setDescricao("Objetiva");
                tipoQuestaoRepository.save(objetiva);

                // Criar nível INTERMEDIÁRIO
                TipoQuestao dissertativa = new TipoQuestao();
                dissertativa.setTipoQuestaoId(2);
                dissertativa.setDescricao("Dissertativa");
                tipoQuestaoRepository.save(dissertativa);

                log.info("Tipo Questão criados: 1=Objetiva, 2=Dissertativa");
            } else {
                log.info("Tipo Questão já existem no banco de dados");
            }
        } catch (Exception e) {
            log.error("Erro ao inicializar Tipo Questão: {}", e.getMessage());
        }
    }

}
