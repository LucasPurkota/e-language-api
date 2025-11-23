--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: alternativa_questao_aula; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.alternativa_questao_aula (
    alternativa_questao_aula_id uuid NOT NULL,
    alternativa character varying(255) NOT NULL,
    descricao character varying(255) NOT NULL,
    questao_aula_id uuid NOT NULL
);


ALTER TABLE public.alternativa_questao_aula OWNER TO lucas_purkota;

--
-- Name: alternativa_questao_personalizada; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.alternativa_questao_personalizada (
    alternativa_questao_personalizada_id uuid NOT NULL,
    alternativa character varying(255) NOT NULL,
    descricao character varying(255) NOT NULL,
    questao_personalizada_id uuid NOT NULL
);


ALTER TABLE public.alternativa_questao_personalizada OWNER TO lucas_purkota;

--
-- Name: aluno_plano; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.aluno_plano (
    aluno_plano_id uuid NOT NULL,
    cvc character varying(255) NOT NULL,
    nome_cartao character varying(255) NOT NULL,
    numero_cartao character varying(255) NOT NULL,
    vencimento_cartao character varying(255) NOT NULL,
    aluno_id uuid NOT NULL,
    forma_pagamento_id integer NOT NULL,
    plano_id uuid NOT NULL
);


ALTER TABLE public.aluno_plano OWNER TO lucas_purkota;

--
-- Name: aluno_professor; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.aluno_professor (
    aluno_professor_id uuid NOT NULL,
    aluno_id uuid NOT NULL,
    professor_id uuid NOT NULL
);


ALTER TABLE public.aluno_professor OWNER TO lucas_purkota;

--
-- Name: aluno_unidade; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.aluno_unidade (
    aluno_unidade_id uuid NOT NULL,
    aluno_id uuid NOT NULL,
    unidade_id uuid NOT NULL,
    status_id integer
);


ALTER TABLE public.aluno_unidade OWNER TO lucas_purkota;

--
-- Name: aula; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.aula (
    aula_id uuid NOT NULL,
    conteudo character varying(255) NOT NULL,
    link_video character varying(255) NOT NULL,
    titulo character varying(255) NOT NULL,
    unidade_id uuid NOT NULL,
    numero integer NOT NULL
);


ALTER TABLE public.aula OWNER TO lucas_purkota;

--
-- Name: avaliacao; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.avaliacao (
    avaliacao_id uuid NOT NULL,
    aprovado character varying(1),
    data_realizacao timestamp(6) without time zone,
    nota double precision,
    aluno_unidade_id uuid NOT NULL,
    status_id integer
);


ALTER TABLE public.avaliacao OWNER TO lucas_purkota;

--
-- Name: avaliacao_questao_aula; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.avaliacao_questao_aula (
    avaliacao_questao_aula_id uuid NOT NULL,
    correto character varying(255),
    resposta character varying(255),
    avaliacao_id uuid NOT NULL,
    questao_aula_id uuid NOT NULL
);


ALTER TABLE public.avaliacao_questao_aula OWNER TO lucas_purkota;

--
-- Name: avaliacao_satisfacao_professor; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.avaliacao_satisfacao_professor (
    avaliacao_satisfacao_professor_id uuid NOT NULL,
    avaliacao character varying(255) NOT NULL,
    pontos integer NOT NULL,
    aluno_professor_id uuid NOT NULL
);


ALTER TABLE public.avaliacao_satisfacao_professor OWNER TO lucas_purkota;

--
-- Name: chat; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.chat (
    chat_id uuid NOT NULL,
    data_envio timestamp(6) without time zone NOT NULL,
    mensagem character varying(255) NOT NULL,
    aluno_professor_id uuid NOT NULL,
    remetente_id uuid NOT NULL
);


ALTER TABLE public.chat OWNER TO lucas_purkota;

--
-- Name: desafio_diario; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.desafio_diario (
    desafio_diario_id uuid NOT NULL,
    correto character varying(1),
    data_desafio date NOT NULL,
    resposta character varying(255),
    perfil_idioma_id uuid NOT NULL,
    questao_aula_id uuid NOT NULL,
    status_id integer NOT NULL
);


ALTER TABLE public.desafio_diario OWNER TO lucas_purkota;

--
-- Name: endereco; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.endereco (
    endereco_id uuid NOT NULL,
    bairro character varying(255) NOT NULL,
    cep character varying(255) NOT NULL,
    cidade character varying(255) NOT NULL,
    complemento character varying(255) NOT NULL,
    logradouro character varying(255) NOT NULL,
    numero integer NOT NULL,
    pais character varying(255) NOT NULL,
    tipo character varying(255) NOT NULL,
    uf character varying(255) NOT NULL,
    usuario_id uuid NOT NULL,
    CONSTRAINT endereco_tipo_check CHECK (((tipo)::text = ANY ((ARRAY['RESIDENCIAL'::character varying, 'COMERCIAL'::character varying])::text[])))
);


ALTER TABLE public.endereco OWNER TO lucas_purkota;

--
-- Name: forma_pagamento; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.forma_pagamento (
    forma_pagamento_id integer NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.forma_pagamento OWNER TO lucas_purkota;

--
-- Name: idioma; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.idioma (
    idioma_id uuid NOT NULL,
    nome character varying(255) NOT NULL,
    sigla character varying(255) NOT NULL
);


ALTER TABLE public.idioma OWNER TO lucas_purkota;

--
-- Name: nivel_dificuldade; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.nivel_dificuldade (
    nivel_dificuldade_id integer NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.nivel_dificuldade OWNER TO lucas_purkota;

--
-- Name: nivel_idioma; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.nivel_idioma (
    nivel_idioma_id integer NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.nivel_idioma OWNER TO lucas_purkota;

--
-- Name: nivelamento; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.nivelamento (
    nivelamento_id uuid NOT NULL,
    data_realizacao timestamp(6) without time zone,
    nota double precision,
    nivel_idioma_id integer,
    status_id integer NOT NULL,
    perfil_idioma_id uuid NOT NULL,
    aprovado character varying(1)
);


ALTER TABLE public.nivelamento OWNER TO lucas_purkota;

--
-- Name: nivelamento_questao_aula; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.nivelamento_questao_aula (
    nivelamento_questao_aula_id uuid NOT NULL,
    resposta character varying(255),
    nivelamento_id uuid NOT NULL,
    questao_aula_id uuid NOT NULL,
    correto character varying(1)
);


ALTER TABLE public.nivelamento_questao_aula OWNER TO lucas_purkota;

--
-- Name: perfil; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.perfil (
    perfil_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    pontos_ranking double precision DEFAULT 0 NOT NULL,
    usuario_id uuid NOT NULL,
    tipo_perfil_id integer NOT NULL
);


ALTER TABLE public.perfil OWNER TO lucas_purkota;

--
-- Name: perfil_idioma; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.perfil_idioma (
    perfil_idioma_id uuid NOT NULL,
    idioma_id uuid NOT NULL,
    nivel_idioma_id integer,
    perfil_id uuid NOT NULL,
    pontos_ranking double precision DEFAULT 0 NOT NULL
);


ALTER TABLE public.perfil_idioma OWNER TO lucas_purkota;

--
-- Name: plano; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.plano (
    plano_id uuid NOT NULL,
    descricao character varying(255) NOT NULL,
    titulo character varying(255) NOT NULL
);


ALTER TABLE public.plano OWNER TO lucas_purkota;

--
-- Name: questao_aula; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.questao_aula (
    questao_aula_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    enunciado character varying(255) NOT NULL,
    gabarito character varying(255),
    nivel_dificuldade_id integer NOT NULL,
    tipo_questao_id integer NOT NULL,
    aula_id uuid NOT NULL
);


ALTER TABLE public.questao_aula OWNER TO lucas_purkota;

--
-- Name: questao_personalizada; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.questao_personalizada (
    questao_personalizada_id uuid NOT NULL,
    enunciado character varying(255) NOT NULL,
    gabarito character varying(255) NOT NULL,
    resposta character varying(255),
    aluno_professor_id uuid NOT NULL,
    idioma_id uuid NOT NULL,
    nivel_dificuldade_id integer NOT NULL,
    tipo_questao_id integer NOT NULL,
    correto character varying(1),
    status_id integer NOT NULL
);


ALTER TABLE public.questao_personalizada OWNER TO lucas_purkota;

--
-- Name: resposta_questao_aula; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.resposta_questao_aula (
    resposta_questao_aula_id uuid NOT NULL,
    resposta character varying(255) NOT NULL,
    aluno_unidade_id uuid NOT NULL,
    questao_aula_id uuid NOT NULL,
    correto character varying(1)
);


ALTER TABLE public.resposta_questao_aula OWNER TO lucas_purkota;

--
-- Name: status; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.status (
    status_id integer NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.status OWNER TO lucas_purkota;

--
-- Name: tipo_endereco; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.tipo_endereco (
    tipo_endereco_id integer NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.tipo_endereco OWNER TO lucas_purkota;

--
-- Name: tipo_perfil; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.tipo_perfil (
    tipo_perfil_id integer NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.tipo_perfil OWNER TO lucas_purkota;

--
-- Name: tipo_questao; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.tipo_questao (
    tipo_questao_id integer NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.tipo_questao OWNER TO lucas_purkota;

--
-- Name: unidade; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.unidade (
    unidade_id uuid NOT NULL,
    conteudo character varying(255) NOT NULL,
    titulo character varying(255) NOT NULL,
    idioma_id uuid NOT NULL,
    nivel_idioma_id integer NOT NULL,
    numero integer NOT NULL
);


ALTER TABLE public.unidade OWNER TO lucas_purkota;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: lucas_purkota
--

CREATE TABLE public.usuario (
    usuario_id uuid NOT NULL,
    cpf character varying(255) NOT NULL,
    data_criacao timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    email character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    senha character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO lucas_purkota;

--
-- Name: alternativa_questao_aula alternativa_questao_aula_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.alternativa_questao_aula
    ADD CONSTRAINT alternativa_questao_aula_pkey PRIMARY KEY (alternativa_questao_aula_id);


--
-- Name: alternativa_questao_aula alternativa_questao_aula_udx; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.alternativa_questao_aula
    ADD CONSTRAINT alternativa_questao_aula_udx UNIQUE (questao_aula_id, alternativa);


--
-- Name: alternativa_questao_personalizada alternativa_questao_personalizada_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.alternativa_questao_personalizada
    ADD CONSTRAINT alternativa_questao_personalizada_pkey PRIMARY KEY (alternativa_questao_personalizada_id);


--
-- Name: aluno_plano aluno_plano_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_plano
    ADD CONSTRAINT aluno_plano_pkey PRIMARY KEY (aluno_plano_id);


--
-- Name: aluno_professor aluno_professor_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_professor
    ADD CONSTRAINT aluno_professor_pkey PRIMARY KEY (aluno_professor_id);


--
-- Name: aluno_unidade aluno_unidade_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_unidade
    ADD CONSTRAINT aluno_unidade_pkey PRIMARY KEY (aluno_unidade_id);


--
-- Name: aula aula_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aula
    ADD CONSTRAINT aula_pkey PRIMARY KEY (aula_id);


--
-- Name: aula aula_udx; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aula
    ADD CONSTRAINT aula_udx UNIQUE (unidade_id, numero);


--
-- Name: avaliacao avaliacao_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT avaliacao_pkey PRIMARY KEY (avaliacao_id);


--
-- Name: avaliacao_questao_aula avaliacao_questao_aula_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.avaliacao_questao_aula
    ADD CONSTRAINT avaliacao_questao_aula_pkey PRIMARY KEY (avaliacao_questao_aula_id);


--
-- Name: avaliacao_satisfacao_professor avaliacao_satisfacao_professor_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.avaliacao_satisfacao_professor
    ADD CONSTRAINT avaliacao_satisfacao_professor_pkey PRIMARY KEY (avaliacao_satisfacao_professor_id);


--
-- Name: chat chat_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.chat
    ADD CONSTRAINT chat_pkey PRIMARY KEY (chat_id);


--
-- Name: desafio_diario desafio_diario_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.desafio_diario
    ADD CONSTRAINT desafio_diario_pkey PRIMARY KEY (desafio_diario_id);


--
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (endereco_id);


--
-- Name: forma_pagamento forma_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.forma_pagamento
    ADD CONSTRAINT forma_pagamento_pkey PRIMARY KEY (forma_pagamento_id);


--
-- Name: idioma idioma_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.idioma
    ADD CONSTRAINT idioma_pkey PRIMARY KEY (idioma_id);


--
-- Name: nivel_dificuldade nivel_dificuldade_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.nivel_dificuldade
    ADD CONSTRAINT nivel_dificuldade_pkey PRIMARY KEY (nivel_dificuldade_id);


--
-- Name: nivel_idioma nivel_idioma_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.nivel_idioma
    ADD CONSTRAINT nivel_idioma_pkey PRIMARY KEY (nivel_idioma_id);


--
-- Name: nivelamento nivelamento_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.nivelamento
    ADD CONSTRAINT nivelamento_pkey PRIMARY KEY (nivelamento_id);


--
-- Name: nivelamento_questao_aula nivelamento_questao_aula_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.nivelamento_questao_aula
    ADD CONSTRAINT nivelamento_questao_aula_pkey PRIMARY KEY (nivelamento_questao_aula_id);


--
-- Name: perfil_idioma perfil_idioma_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.perfil_idioma
    ADD CONSTRAINT perfil_idioma_pkey PRIMARY KEY (perfil_idioma_id);


--
-- Name: perfil perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (perfil_id);


--
-- Name: plano plano_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (plano_id);


--
-- Name: questao_aula questao_aula_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.questao_aula
    ADD CONSTRAINT questao_aula_pkey PRIMARY KEY (questao_aula_id);


--
-- Name: questao_personalizada questao_personalizada_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.questao_personalizada
    ADD CONSTRAINT questao_personalizada_pkey PRIMARY KEY (questao_personalizada_id);


--
-- Name: resposta_questao_aula resposta_questao_aula_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.resposta_questao_aula
    ADD CONSTRAINT resposta_questao_aula_pkey PRIMARY KEY (resposta_questao_aula_id);


--
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (status_id);


--
-- Name: tipo_endereco tipo_endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.tipo_endereco
    ADD CONSTRAINT tipo_endereco_pkey PRIMARY KEY (tipo_endereco_id);


--
-- Name: tipo_perfil tipo_perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.tipo_perfil
    ADD CONSTRAINT tipo_perfil_pkey PRIMARY KEY (tipo_perfil_id);


--
-- Name: tipo_questao tipo_questao_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.tipo_questao
    ADD CONSTRAINT tipo_questao_pkey PRIMARY KEY (tipo_questao_id);


--
-- Name: usuario uk5171l57faosmj8myawaucatdw; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk5171l57faosmj8myawaucatdw UNIQUE (email);


--
-- Name: idioma uk5xwfmvx3k6i4kqr9r7snyciwq; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.idioma
    ADD CONSTRAINT uk5xwfmvx3k6i4kqr9r7snyciwq UNIQUE (sigla);


--
-- Name: alternativa_questao_personalizada uk7pp4bkgtknj5t411oe1xw2ept; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.alternativa_questao_personalizada
    ADD CONSTRAINT uk7pp4bkgtknj5t411oe1xw2ept UNIQUE (descricao);


--
-- Name: alternativa_questao_personalizada uk8l861723ru8y7uwg4s3mj9sfc; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.alternativa_questao_personalizada
    ADD CONSTRAINT uk8l861723ru8y7uwg4s3mj9sfc UNIQUE (alternativa);


--
-- Name: idioma ukdev6dxjyriha4pacjt7yh1i7; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.idioma
    ADD CONSTRAINT ukdev6dxjyriha4pacjt7yh1i7 UNIQUE (nome);


--
-- Name: unidade unidade_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.unidade
    ADD CONSTRAINT unidade_pkey PRIMARY KEY (unidade_id);


--
-- Name: unidade unidade_udx; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.unidade
    ADD CONSTRAINT unidade_udx UNIQUE (idioma_id, numero);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usuario_id);


--
-- Name: alternativa_questao_aula alternativa_questao_aula_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.alternativa_questao_aula
    ADD CONSTRAINT alternativa_questao_aula_fk FOREIGN KEY (questao_aula_id) REFERENCES public.questao_aula(questao_aula_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: alternativa_questao_personalizada alternativa_questao_personalizada_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.alternativa_questao_personalizada
    ADD CONSTRAINT alternativa_questao_personalizada_fk FOREIGN KEY (questao_personalizada_id) REFERENCES public.questao_personalizada(questao_personalizada_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: aluno_plano aluno_palno_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_plano
    ADD CONSTRAINT aluno_palno_fk FOREIGN KEY (aluno_id) REFERENCES public.perfil(perfil_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: aluno_plano aluno_plano_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_plano
    ADD CONSTRAINT aluno_plano_fk2 FOREIGN KEY (plano_id) REFERENCES public.plano(plano_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: aluno_plano aluno_plano_fk3; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_plano
    ADD CONSTRAINT aluno_plano_fk3 FOREIGN KEY (forma_pagamento_id) REFERENCES public.forma_pagamento(forma_pagamento_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: aluno_professor aluno_professor_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_professor
    ADD CONSTRAINT aluno_professor_fk2 FOREIGN KEY (professor_id) REFERENCES public.perfil(perfil_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: aluno_unidade aluno_unidade_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_unidade
    ADD CONSTRAINT aluno_unidade_fk FOREIGN KEY (aluno_id) REFERENCES public.perfil(perfil_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: aluno_unidade aluno_unidade_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_unidade
    ADD CONSTRAINT aluno_unidade_fk2 FOREIGN KEY (unidade_id) REFERENCES public.unidade(unidade_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: aluno_unidade aluno_unidade_fk3; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_unidade
    ADD CONSTRAINT aluno_unidade_fk3 FOREIGN KEY (status_id) REFERENCES public.status(status_id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- Name: aula aula_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aula
    ADD CONSTRAINT aula_fk2 FOREIGN KEY (unidade_id) REFERENCES public.unidade(unidade_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: avaliacao avaliacao_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT avaliacao_fk FOREIGN KEY (aluno_unidade_id) REFERENCES public.aluno_unidade(aluno_unidade_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: avaliacao avaliacao_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT avaliacao_fk2 FOREIGN KEY (status_id) REFERENCES public.status(status_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: avaliacao_satisfacao_professor avaliacao_satisfacao_professor_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.avaliacao_satisfacao_professor
    ADD CONSTRAINT avaliacao_satisfacao_professor_fk FOREIGN KEY (aluno_professor_id) REFERENCES public.aluno_professor(aluno_professor_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: chat chat_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.chat
    ADD CONSTRAINT chat_fk FOREIGN KEY (aluno_professor_id) REFERENCES public.aluno_professor(aluno_professor_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: chat chat_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.chat
    ADD CONSTRAINT chat_fk2 FOREIGN KEY (remetente_id) REFERENCES public.perfil(perfil_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: desafio_diario desafio_diario_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.desafio_diario
    ADD CONSTRAINT desafio_diario_fk FOREIGN KEY (perfil_idioma_id) REFERENCES public.perfil_idioma(perfil_idioma_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: desafio_diario desafio_diario_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.desafio_diario
    ADD CONSTRAINT desafio_diario_fk2 FOREIGN KEY (questao_aula_id) REFERENCES public.questao_aula(questao_aula_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: desafio_diario desafio_diario_fk3; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.desafio_diario
    ADD CONSTRAINT desafio_diario_fk3 FOREIGN KEY (status_id) REFERENCES public.status(status_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: endereco endereco_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(usuario_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: aluno_professor endereco_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.aluno_professor
    ADD CONSTRAINT endereco_fk FOREIGN KEY (aluno_id) REFERENCES public.perfil(perfil_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: questao_personalizada idioma_id; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.questao_personalizada
    ADD CONSTRAINT idioma_id FOREIGN KEY (idioma_id) REFERENCES public.idioma(idioma_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: nivelamento nivelamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.nivelamento
    ADD CONSTRAINT nivelamento_fk FOREIGN KEY (perfil_idioma_id) REFERENCES public.perfil_idioma(perfil_idioma_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: nivelamento nivelamento_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.nivelamento
    ADD CONSTRAINT nivelamento_fk2 FOREIGN KEY (nivel_idioma_id) REFERENCES public.nivel_idioma(nivel_idioma_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: nivelamento nivelamento_fk3; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.nivelamento
    ADD CONSTRAINT nivelamento_fk3 FOREIGN KEY (status_id) REFERENCES public.status(status_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: nivelamento_questao_aula nivelamento_questao_aula_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.nivelamento_questao_aula
    ADD CONSTRAINT nivelamento_questao_aula_fk FOREIGN KEY (nivelamento_id) REFERENCES public.nivelamento(nivelamento_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: nivelamento_questao_aula nivelamento_questao_aula_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.nivelamento_questao_aula
    ADD CONSTRAINT nivelamento_questao_aula_fk2 FOREIGN KEY (questao_aula_id) REFERENCES public.questao_aula(questao_aula_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: perfil perfil_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(usuario_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: perfil perfil_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_fk2 FOREIGN KEY (tipo_perfil_id) REFERENCES public.tipo_perfil(tipo_perfil_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: perfil_idioma perfil_idioma_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.perfil_idioma
    ADD CONSTRAINT perfil_idioma_fk FOREIGN KEY (perfil_id) REFERENCES public.perfil(perfil_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: perfil_idioma perfil_idioma_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.perfil_idioma
    ADD CONSTRAINT perfil_idioma_fk2 FOREIGN KEY (idioma_id) REFERENCES public.idioma(idioma_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: perfil_idioma qperfil_idioma_fk3; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.perfil_idioma
    ADD CONSTRAINT qperfil_idioma_fk3 FOREIGN KEY (nivel_idioma_id) REFERENCES public.nivel_idioma(nivel_idioma_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: questao_aula questao_aula_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.questao_aula
    ADD CONSTRAINT questao_aula_fk FOREIGN KEY (nivel_dificuldade_id) REFERENCES public.nivel_dificuldade(nivel_dificuldade_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: avaliacao_questao_aula questao_aula_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.avaliacao_questao_aula
    ADD CONSTRAINT questao_aula_fk FOREIGN KEY (avaliacao_id) REFERENCES public.avaliacao(avaliacao_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: questao_aula questao_aula_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.questao_aula
    ADD CONSTRAINT questao_aula_fk2 FOREIGN KEY (tipo_questao_id) REFERENCES public.tipo_questao(tipo_questao_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: questao_personalizada questao_personalizada_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.questao_personalizada
    ADD CONSTRAINT questao_personalizada_fk2 FOREIGN KEY (aluno_professor_id) REFERENCES public.aluno_professor(aluno_professor_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: questao_personalizada questao_personalizada_fk3; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.questao_personalizada
    ADD CONSTRAINT questao_personalizada_fk3 FOREIGN KEY (nivel_dificuldade_id) REFERENCES public.nivel_dificuldade(nivel_dificuldade_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: questao_personalizada questao_personalizada_fk4; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.questao_personalizada
    ADD CONSTRAINT questao_personalizada_fk4 FOREIGN KEY (tipo_questao_id) REFERENCES public.tipo_questao(tipo_questao_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: questao_personalizada questao_personalizada_fk5; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.questao_personalizada
    ADD CONSTRAINT questao_personalizada_fk5 FOREIGN KEY (status_id) REFERENCES public.status(status_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: resposta_questao_aula resposta_questao_aula_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.resposta_questao_aula
    ADD CONSTRAINT resposta_questao_aula_fk FOREIGN KEY (aluno_unidade_id) REFERENCES public.aluno_unidade(aluno_unidade_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: resposta_questao_aula resposta_questao_aula_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.resposta_questao_aula
    ADD CONSTRAINT resposta_questao_aula_fk2 FOREIGN KEY (questao_aula_id) REFERENCES public.questao_aula(questao_aula_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: unidade unidade_fk; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.unidade
    ADD CONSTRAINT unidade_fk FOREIGN KEY (idioma_id) REFERENCES public.idioma(idioma_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: unidade unidade_fk2; Type: FK CONSTRAINT; Schema: public; Owner: lucas_purkota
--

ALTER TABLE ONLY public.unidade
    ADD CONSTRAINT unidade_fk2 FOREIGN KEY (nivel_idioma_id) REFERENCES public.nivel_idioma(nivel_idioma_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

