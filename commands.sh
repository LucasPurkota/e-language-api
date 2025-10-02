#!/bin/bash

# Script de comandos rápidos para E-Language API
# Uso: ./commands.sh [comando]

# Cores
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

case "$1" in
    "rebuild")
        echo -e "${BLUE}🔄 Rebuild Completo (Backend + Frontend)${NC}"
        docker-compose down
        mvn clean package -DskipTests
        docker-compose up --build -d
        echo -e "${GREEN}✅ Rebuild completo finalizado!${NC}"
        echo -e "${YELLOW}🌐 Frontend: http://localhost:3000${NC}"
        echo -e "${YELLOW}🔧 Backend: http://localhost:8080${NC}"
        ;;
    
    "rebuild-back")
        echo -e "${BLUE}⚡ Rebuild Rápido (só backend)${NC}"
        docker-compose stop backend
        docker-compose rm -f backend
        mvn package -DskipTests
        docker-compose up --build -d backend
        echo -e "${GREEN}✅ Rebuild backend finalizado!${NC}"
        ;;

    "rebuild-front")
        echo -e "${BLUE}🎨 Rebuild Frontend${NC}"
        docker-compose stop mini-frontend
        docker-compose rm -f mini-frontend
        docker-compose up --build -d mini-frontend
        echo -e "${GREEN}✅ Rebuild frontend finalizado!${NC}"
        echo -e "${YELLOW}🌐 Frontend disponível em: http://localhost:3000${NC}"
        ;;
    
    "quick")
        echo -e "${BLUE}⚡ Rebuild Rápido (só backend) - DEPRECATED${NC}"
        echo -e "${YELLOW}Use: ./commands.sh rebuild-back${NC}"
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
        echo -e "${YELLOW}🌐 Frontend: http://localhost:3000${NC}"
        echo -e "${YELLOW}🔧 Backend: http://localhost:8080${NC}"
        ;;

    "start-front")
        echo -e "${BLUE}▶️ Iniciando apenas frontend${NC}"
        docker-compose up -d mini-frontend
        echo -e "${GREEN}✅ Frontend iniciado!${NC}"
        echo -e "${YELLOW}🌐 Frontend: http://localhost:3000${NC}"
        ;;

    "start-back")
        echo -e "${BLUE}▶️ Iniciando backend e database${NC}"
        docker-compose up -d db backend
        echo -e "${GREEN}✅ Backend iniciado!${NC}"
        echo -e "${YELLOW}🔧 Backend: http://localhost:8080${NC}"
        ;;
    
    "stop")
        echo -e "${BLUE}⏹️ Parando containers${NC}"
        docker-compose down
        echo -e "${GREEN}✅ Containers parados!${NC}"
        ;;

    "stop-front")
        echo -e "${BLUE}⏹️ Parando apenas frontend${NC}"
        docker-compose stop mini-frontend
        echo -e "${GREEN}✅ Frontend parado!${NC}"
        ;;

    "stop-back")
        echo -e "${BLUE}⏹️ Parando backend${NC}"
        docker-compose stop backend
        echo -e "${GREEN}✅ Backend parado!${NC}"
        ;;
    
    "logs")
        echo -e "${BLUE}📋 Logs do backend${NC}"
        docker-compose logs -f backend
        ;;

    "logs-front")
        echo -e "${BLUE}📋 Logs do frontend${NC}"
        docker-compose logs -f mini-frontend
        ;;

    "logs-all")
        echo -e "${BLUE}📋 Logs de todos os serviços${NC}"
        docker-compose logs -f
        ;;
    
    "status")
        echo -e "${BLUE}📊 Status dos containers${NC}"
        docker-compose ps
        echo -e "\n${BLUE}Status da aplicação:${NC}"
        curl -s -o /dev/null -w "Backend API: %{http_code}\n" http://localhost:8080/v3/api-docs
        curl -s -o /dev/null -w "Swagger UI: %{http_code}\n" http://localhost:8080/swagger-ui/index.html
        curl -s -o /dev/null -w "Frontend: %{http_code}\n" http://localhost:3000
        ;;
    
    "clean")
        echo -e "${BLUE}🧹 Limpando tudo${NC}"
        docker-compose down -v --remove-orphans
        docker system prune -f
        echo -e "${GREEN}✅ Limpeza completa!${NC}"
        ;;

    "dev-front")
        echo -e "${BLUE}🚀 Modo desenvolvimento frontend (local)${NC}"
        echo -e "${YELLOW}Certifique-se de que o backend está rodando em http://localhost:8080${NC}"
        cd frontend && npm install && npm start
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

    "test-front")
        echo -e "${BLUE}🧪 Testando conexão frontend -> backend${NC}"
        echo -e "${YELLOW}Verificando se o frontend consegue acessar a API...${NC}"
        curl -s http://localhost:3000 > /dev/null && echo -e "${GREEN}✅ Frontend acessível${NC}" || echo -e "${RED}❌ Frontend inacessível${NC}"
        curl -s http://localhost:8080/api/v1/usuarios > /dev/null && echo -e "${GREEN}✅ API backend diretamente acessível${NC}" || echo -e "${RED}❌ API backend diretamente inacessível${NC}"
        echo -e "${YELLOW}Verificando se o proxy reverso do Nginx está funcionando...${NC}"
        curl -s http://localhost:3000/api/v1/usuarios > /dev/null && echo -e "${GREEN}✅ API via proxy Nginx acessível${NC}" || echo -e "${RED}❌ API via proxy Nginx inacessível${NC}"
        ;;
        
    "debug-proxy")
        echo -e "${BLUE}🔍 Debugando configuração de proxy reverso${NC}"
        echo -e "${YELLOW}Verificando se o backend está acessível internamente pelo frontend...${NC}"
        docker exec -it e-language-frontend wget -O- --timeout=2 http://backend:8080/api/v1/usuarios || echo "Falha na conexão interna"
        echo -e "\n${YELLOW}Verificando a configuração do Nginx...${NC}"
        docker exec -it e-language-frontend cat /etc/nginx/conf.d/default.conf
        echo -e "\n${YELLOW}Verificando logs de erro do Nginx...${NC}"
        docker exec -it e-language-frontend tail /var/log/nginx/error.log
        ;;
    
    *)
        echo -e "${BLUE}E-Language API - Comandos Disponíveis:${NC}"
        echo ""
        echo -e "${YELLOW}🔄 Build & Deploy:${NC}"
        echo "  ./commands.sh rebuild        - Rebuild completo (backend + frontend)"
        echo "  ./commands.sh rebuild-back   - Rebuild apenas backend"
        echo "  ./commands.sh rebuild-front  - Rebuild apenas frontend"
        echo "  ./commands.sh compile        - Compilar projeto Maven"
        echo ""
        echo -e "${YELLOW}▶️ Controle de Serviços:${NC}"
        echo "  ./commands.sh start          - Iniciar todos os containers"
        echo "  ./commands.sh start-back     - Iniciar backend + database"
        echo "  ./commands.sh start-front    - Iniciar apenas frontend"
        echo "  ./commands.sh stop           - Parar todos os containers"
        echo "  ./commands.sh stop-back      - Parar apenas backend"
        echo "  ./commands.sh stop-front     - Parar apenas frontend"
        echo ""
        echo -e "${YELLOW}📋 Logs & Monitoramento:${NC}"
        echo "  ./commands.sh logs           - Logs do backend"
        echo "  ./commands.sh logs-front     - Logs do frontend"
        echo "  ./commands.sh logs-all       - Logs de todos os serviços"
        echo "  ./commands.sh status         - Status de todos os serviços"
        echo ""
        echo -e "${YELLOW}🧪 Testes & Desenvolvimento:${NC}"
        echo "  ./commands.sh test           - Testar endpoint de criação"
        echo "  ./commands.sh test-front     - Testar conexão frontend/backend"
        echo "  ./commands.sh debug-proxy    - Debugar configuração do proxy reverso"
        echo "  ./commands.sh dev-front      - Rodar frontend em modo desenvolvimento"
        echo "  ./commands.sh clean          - Limpar tudo"
        echo ""
        echo -e "${BLUE}URLs importantes:${NC}"
        echo "  🌐 Frontend:    http://localhost:3000"
        echo "  🔧 Backend:     http://localhost:8080"
        echo "  📚 Swagger UI:  http://localhost:8080/swagger-ui/index.html"
        echo "  📋 API Docs:    http://localhost:8080/v3/api-docs"
        echo "  🗄️ Database:    localhost:5432 (user: asdf, password: asdgf)"
        ;;
esac