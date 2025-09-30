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
    echo "1) 🔄 Rebuild Completo (stop + compile + build + start)"
    echo "2) ⚡ Rebuild Rápido (só backend)"
    echo "3) 🛠️  Compilar Projeto"
    echo "4) 🐳 Buildar e Iniciar Containers"
    echo "5) ▶️  Iniciar Containers"
    echo "6) ⏹️  Parar Containers"
    echo "7) 📋 Ver Logs"
    echo "8) 📊 Mostrar Status"
    echo "9) 🧪 Testar Endpoints"
    echo "10) 🧹 Limpar Tudo"
    echo "0) ❌ Sair"
    echo -e "${BLUE}================================${NC}"
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
            compile_project
            ;;
        4)
            build_and_start
            ;;
        5)
            start_containers
            ;;
        6)
            stop_containers
            ;;
        7)
            show_logs
            ;;
        8)
            show_status
            ;;
        9)
            test_endpoints
            ;;
        10)
            clean_all
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