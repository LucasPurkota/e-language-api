#!/bin/bash

# Script de comandos rápidos para E-Language API
# Uso: ./commands.sh [comando]

# Cores
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m'

case "$1" in
    "rebuild")
        echo -e "${BLUE}🔄 Rebuild Completo${NC}"
        docker-compose down
        mvn clean package -DskipTests
        docker-compose up --build -d
        echo -e "${GREEN}✅ Rebuild completo finalizado!${NC}"
        ;;
    
    "quick")
        echo -e "${BLUE}⚡ Rebuild Rápido (só backend)${NC}"
        docker-compose stop backend
        docker-compose rm -f backend
        mvn package -DskipTests
        docker-compose up --build -d backend
        echo -e "${GREEN}✅ Rebuild rápido finalizado!${NC}"
        ;;
    
    "compile")
        echo -e "${BLUE}🛠️ Compilando projeto${NC}"
        mvn clean package -DskipTests
        echo -e "${GREEN}✅ Compilação finalizada!${NC}"
        ;;
    
    "start")
        echo -e "${BLUE}▶️ Iniciando containers${NC}"
        docker-compose up -d
        echo -e "${GREEN}✅ Containers iniciados!${NC}"
        ;;
    
    "stop")
        echo -e "${BLUE}⏹️ Parando containers${NC}"
        docker-compose down
        echo -e "${GREEN}✅ Containers parados!${NC}"
        ;;
    
    "logs")
        echo -e "${BLUE}📋 Logs do backend${NC}"
        docker-compose logs -f backend
        ;;
    
    "status")
        echo -e "${BLUE}📊 Status dos containers${NC}"
        docker-compose ps
        echo -e "\n${BLUE}Status da aplicação:${NC}"
        curl -s -o /dev/null -w "Swagger UI: %{http_code}\n" http://localhost:8080/swagger-ui/index.html
        curl -s -o /dev/null -w "API Docs: %{http_code}\n" http://localhost:8080/v3/api-docs
        ;;
    
    "clean")
        echo -e "${BLUE}🧹 Limpando tudo${NC}"
        docker-compose down -v --remove-orphans
        docker system prune -f
        echo -e "${GREEN}✅ Limpeza completa!${NC}"
        ;;
    
    "test")
        echo -e "${BLUE}🧪 Testando criação de usuário${NC}"
        curl -X POST http://localhost:8080/api/v1/usuarios \
          -H "Content-Type: application/json" \
          -d '{
            "nome": "Teste Automático",
            "cpf": "71756301077",
            "email": "teste.auto@email.com",
            "senha": "TesteAuto@123"
          }'
        echo -e "\n${GREEN}✅ Teste finalizado!${NC}"
        ;;
    
    *)
        echo -e "${BLUE}E-Language API - Comandos Disponíveis:${NC}"
        echo ""
        echo "  ./commands.sh rebuild    - Rebuild completo (stop + compile + build + start)"
        echo "  ./commands.sh quick      - Rebuild rápido (só backend)"
        echo "  ./commands.sh compile    - Compilar projeto Maven"
        echo "  ./commands.sh start      - Iniciar containers"
        echo "  ./commands.sh stop       - Parar containers"
        echo "  ./commands.sh logs       - Ver logs do backend"
        echo "  ./commands.sh status     - Mostrar status"
        echo "  ./commands.sh clean      - Limpar tudo"
        echo "  ./commands.sh test       - Testar endpoint de criação"
        echo ""
        echo -e "${BLUE}URLs importantes:${NC}"
        echo "  Swagger UI: http://localhost:8080/swagger-ui/index.html"
        echo "  API Docs:   http://localhost:8080/v3/api-docs"
        ;;
esac