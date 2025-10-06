#!/bin/bash

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Função para imprimir mensagens coloridas
print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Função para verificar se o comando foi executado com sucesso
check_command() {
    if [ $? -eq 0 ]; then
        print_success "$1"
    else
        print_error "Falha ao executar: $1"
        exit 1
    fi
}

# Função para parar containers
stop_containers() {
    print_info "Parando containers..."
    docker-compose down
    check_command "Containers parados"
}

# Função para compilar o projeto
compile_project() {
    print_info "Compilando projeto Maven..."
    mvn clean package -DskipTests
    check_command "Projeto compilado"
}

# Função para buildar e subir containers
build_and_start() {
    print_info "Buildando e iniciando containers..."
    docker-compose up --build -d
    check_command "Containers buildados e iniciados"
}

# Função para subir containers (sem rebuild)
start_containers() {
    print_info "Iniciando containers..."
    docker-compose up -d
    check_command "Containers iniciados"
}

# Função para ver logs
show_logs() {
    print_info "Mostrando logs do backend..."
    docker-compose logs -f backend
}

# Função para rebuild completo
full_rebuild() {
    print_info "Iniciando rebuild completo..."
    stop_containers
    compile_project
    build_and_start
    print_success "Rebuild completo finalizado!"
    print_info "Aguardando aplicação inicializar..."
    sleep 15
    print_info "Testando endpoints..."
    echo "Swagger UI: http://localhost:8080/swagger-ui/index.html"
    echo "API Docs: http://localhost:8080/v3/api-docs"
}

# Função para rebuild rápido (sem parar DB)
quick_rebuild() {
    print_info "Iniciando rebuild rápido..."
    print_info "Parando apenas o backend..."
    docker-compose stop backend
    docker-compose rm -f backend
    compile_project
    print_info "Buildando e iniciando backend..."
    docker-compose up --build -d backend
    check_command "Rebuild rápido finalizado"
}

# Função para limpar tudo
clean_all() {
    print_warning "Isso vai remover TODOS os containers, volumes e imagens!"
    read -p "Tem certeza? (y/N): " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        print_info "Limpando containers e volumes..."
        docker-compose down -v --remove-orphans
        docker system prune -f
        print_success "Limpeza completa realizada"
    else
        print_info "Operação cancelada"
    fi
}

# Função para mostrar status
show_status() {
    print_info "Status dos containers:"
    docker-compose ps
    echo
    print_info "Status da aplicação:"
    if curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/swagger-ui/index.html | grep -q "200"; then
        print_success "Swagger UI: ✅ Funcionando (http://localhost:8080/swagger-ui/index.html)"
    else
        print_error "Swagger UI: ❌ Não disponível"
    fi
    
    if curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/v3/api-docs | grep -q "200"; then
        print_success "API Docs: ✅ Funcionando (http://localhost:8080/v3/api-docs)"
    else
        print_error "API Docs: ❌ Não disponível"
    fi
}

# Função para testar endpoints
test_endpoints() {
    print_info "Testando endpoints básicos..."
    
    echo "=== Testando criação de usuário ==="
    curl -X POST http://localhost:8080/api/v1/usuarios \
      -H "Content-Type: application/json" \
      -d '{
        "nome": "Teste Script",
        "cpf": "71756301077",
        "email": "teste.script@email.com",
        "senha": "TesteScrip@123"
      }' | jq .
    
    echo -e "\n=== Testando autenticação ==="
    curl -X POST http://localhost:8080/api/v1/auth \
      -H "Content-Type: application/json" \
      -d '{
        "email": "teste.script@email.com",
        "senha": "TesteScrip@123"
      }' | jq .
}

# Menu principal
show_menu() {
    echo -e "${BLUE}================================${NC}"
    echo -e "${BLUE}    E-Language API - Dev Script${NC}"
    echo -e "${BLUE}================================${NC}"
    echo -e "${YELLOW}[Build & Deploy]${NC}"
    echo "1) 🔄 Rebuild Completo (backend + frontend)"
    echo "2) ⚡ Rebuild Backend"
    echo "3) 🎨 Rebuild Frontend"
    echo "4) 🛠️ Compilar Projeto Maven"
    
    echo -e "\n${YELLOW}[Controle de Serviços]${NC}"
    echo "5) ▶️  Iniciar Todos Containers"
    echo "6) ▶️  Iniciar Backend + Database"
    echo "7) ▶️  Iniciar Frontend"
    echo "8) ⏹️  Parar Todos Containers"
    echo "9) ⏹️  Parar Backend"
    echo "10) ⏹️ Parar Frontend"
    
    echo -e "\n${YELLOW}[Monitoramento]${NC}"
    echo "11) 📋 Ver Logs Backend"
    echo "12) 📋 Ver Logs Frontend"
    echo "13) 📋 Ver Todos os Logs"
    echo "14) 📊 Mostrar Status"
    
    echo -e "\n${YELLOW}[Testes]${NC}"
    echo "15) 🧪 Testar API (usuários)"
    echo "16) 🧪 Testar Conexão Frontend-Backend"
    echo "17) 🔍 Debugar Proxy Reverso"
    
    echo -e "\n${YELLOW}[Diversos]${NC}"
    echo "18) 🧹 Limpar Tudo"
    echo "19) 💻 Rodar Frontend em Modo Desenvolvimento"
    echo "0) ❌ Sair"
    echo -e "${BLUE}================================${NC}"
}

# Funções para o frontend
rebuild_frontend() {
    print_info "Rebuilding frontend..."
    docker-compose stop mini-frontend
    docker-compose rm -f mini-frontend
    docker-compose up --build -d mini-frontend
    check_command "Frontend rebuilded e iniciado"
    echo "Frontend disponível em: http://localhost:3000"
}

start_frontend() {
    print_info "Iniciando apenas frontend..."
    docker-compose up -d mini-frontend
    check_command "Frontend iniciado"
    echo "Frontend disponível em: http://localhost:3000"
}

stop_frontend() {
    print_info "Parando frontend..."
    docker-compose stop mini-frontend
    check_command "Frontend parado"
}

show_frontend_logs() {
    print_info "Mostrando logs do frontend..."
    docker-compose logs -f mini-frontend
}

show_all_logs() {
    print_info "Mostrando logs de todos os serviços..."
    docker-compose logs -f
}

start_backend() {
    print_info "Iniciando backend e database..."
    docker-compose up -d db backend
    check_command "Backend e database iniciados"
}

stop_backend() {
    print_info "Parando backend..."
    docker-compose stop backend
    check_command "Backend parado"
}

test_frontend_connection() {
    print_info "Testando conexão frontend-backend..."
    curl -s http://localhost:3000 > /dev/null && print_success "✅ Frontend acessível" || print_error "❌ Frontend inacessível"
    curl -s http://localhost:8080/api/v1/usuarios > /dev/null && print_success "✅ API backend diretamente acessível" || print_error "❌ API backend diretamente inacessível"
    print_info "Verificando se o proxy reverso do Nginx está funcionando..."
    curl -s http://localhost:3000/api/v1/usuarios > /dev/null && print_success "✅ API via proxy Nginx acessível" || print_error "❌ API via proxy Nginx inacessível"
}

debug_proxy() {
    print_info "Debugando configuração de proxy reverso..."
    print_info "Verificando se o backend está acessível internamente pelo frontend..."
    docker exec -it e-language-frontend wget -O- --timeout=2 http://backend:8080/api/v1/usuarios || echo "Falha na conexão interna"
    print_info "Verificando a configuração do Nginx..."
    docker exec -it e-language-frontend cat /etc/nginx/conf.d/default.conf
    print_info "Verificando logs de erro do Nginx..."
    docker exec -it e-language-frontend tail /var/log/nginx/error.log
}

run_frontend_dev() {
    print_info "Iniciando frontend em modo desenvolvimento..."
    print_info "Certifique-se de que o backend está rodando com ./commands.sh start-back"
    cd frontend && npm install && npm start
}

# Loop principal
while true; do
    show_menu
    read -p "Escolha uma opção: " choice
    
    case $choice in
        1)
            full_rebuild
            ;;
        2)
            quick_rebuild
            ;;
        3)
            rebuild_frontend
            ;;
        4)
            compile_project
            ;;
        5)
            start_containers
            ;;
        6)
            start_backend
            ;;
        7)
            start_frontend
            ;;
        8)
            stop_containers
            ;;
        9)
            stop_backend
            ;;
        10)
            stop_frontend
            ;;
        11)
            show_logs
            ;;
        12)
            show_frontend_logs
            ;;
        13)
            show_all_logs
            ;;
        14)
            show_status
            ;;
        15)
            test_endpoints
            ;;
        16)
            test_frontend_connection
            ;;
        17)
            debug_proxy
            ;;
        18)
            clean_all
            ;;
        19)
            run_frontend_dev
            ;;
        0)
            print_info "Saindo..."
            exit 0
            ;;
        *)
            print_error "Opção inválida!"
            ;;
    esac
    
    echo
    read -p "Pressione Enter para continuar..."
    clear
done