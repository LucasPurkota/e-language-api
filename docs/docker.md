# Rodando com Docker

1. Instale Docker e Docker Compose.
2. Execute:
   ```bash
   docker-compose up -d
    ```
   obs: está em desenvolvimento, pode haver mudanças.

3. A API estará disponível em `http://localhost:8080"`.

# Resolução de problemas
Se você encontrar problemas ao rodar a API (ex: endpoints não disponíveis, alterações não refletidas), siga estes passos para garantir que tudo está atualizado:
# 1. Recompile o projeto Java (gera o .jar atualizado)
mvn clean package -DskipTests

# 2. Pare e remova os containers existentes
docker-compose down

# 3. Suba os containers novamente, forçando o rebuild das imagens
docker-compose up --build