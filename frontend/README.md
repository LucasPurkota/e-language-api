# E-Language API - Frontend Minimalista

Este é um frontend Angular minimalista criado para testar a comunicação com o backend E-Language API.

## Estrutura do Projeto

O projeto foi simplificado para incluir apenas:

1. **Listagem de Usuários** - Exibe todos os usuários cadastrados
2. **Criação de Usuário** - Formulário simples para cadastro de usuário

## Componentes Principais

- `app.component.ts` - Componente raiz que define o layout básico
- `usuario-list.component.ts` - Lista os usuários do sistema
- `usuario-form.component.ts` - Formulário para criar usuário
- `usuario.service.ts` - Serviço que comunica com a API backend
- `usuario.model.ts` - Interfaces que definem a estrutura de dados

## Comunicação com o Backend

A comunicação entre o frontend e backend é feita através do serviço `usuario.service.ts`, que utiliza o `HttpClient` do Angular para fazer requisições HTTP.

O arquivo `environment.ts` define a URL base da API como `/api/v1`, permitindo que o Nginx atue como proxy reverso para encaminhar as requisições para o backend.

## Como Testar

1. Acesse a página principal em http://localhost:3000
2. Você verá uma lista dos usuários cadastrados
3. Clique em "Novo Usuário" para criar um novo registro
4. Preencha o formulário e clique em "Criar"
5. O novo usuário aparecerá na listagem

## Fluxo de Dados

```
Browser -> Nginx (:3000) -> Spring Boot (:8080) -> PostgreSQL (:5432)
```

1. O navegador carrega a aplicação Angular do Nginx
2. Angular faz requisições HTTP para `/api/v1/...`
3. Nginx encaminha essas requisições para `http://backend:8080/api/v1/...`
4. Spring Boot processa as requisições e acessa o PostgreSQL
5. Os dados retornam pelo mesmo caminho
