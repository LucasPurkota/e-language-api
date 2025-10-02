@echo off
REM Script de comandos para Windows - E-Language API
REM Uso: commands.bat [comando]

if "%1"=="rebuild" (
    echo 🔄 Rebuild Completo
    docker-compose down
    mvn clean package -DskipTests
    docker-compose up --build -d
    echo ✅ Rebuild completo finalizado!
    goto end
)

if "%1"=="quick" (
    echo ⚡ Rebuild Rápido ^(só backend^)
    docker-compose stop backend
    docker-compose rm -f backend
    mvn package -DskipTests
    docker-compose up --build -d backend
    echo ✅ Rebuild rápido finalizado!
    goto end
)

if "%1"=="compile" (
    echo 🛠️ Compilando projeto
    mvn clean package -DskipTests
    echo ✅ Compilação finalizada!
    goto end
)

if "%1"=="start" (
    echo ▶️ Iniciando containers
    docker-compose up -d
    echo ✅ Containers iniciados!
    goto end
)

if "%1"=="stop" (
    echo ⏹️ Parando containers
    docker-compose down
    echo ✅ Containers parados!
    goto end
)

if "%1"=="logs" (
    echo 📋 Logs do backend
    docker-compose logs -f backend
    goto end
)

if "%1"=="status" (
    echo 📊 Status dos containers
    docker-compose ps
    echo.
    echo Status da aplicação:
    curl -s -o NUL -w "Swagger UI: %%{http_code}" http://localhost:8080/swagger-ui/index.html
    echo.
    curl -s -o NUL -w "API Docs: %%{http_code}" http://localhost:8080/v3/api-docs
    echo.
    goto end
)

if "%1"=="clean" (
    echo 🧹 Limpando tudo
    docker-compose down -v --remove-orphans
    docker system prune -f
    echo ✅ Limpeza completa!
    goto end
)

if "%1"=="test" (
    echo 🧪 Testando criação de usuário
    curl -X POST http://localhost:8080/api/v1/usuarios -H "Content-Type: application/json" -d "{\"nome\": \"Teste Automático\", \"cpf\": \"71756301077\", \"email\": \"teste.auto@email.com\", \"senha\": \"TesteAuto@123\"}"
    echo.
    echo ✅ Teste finalizado!
    goto end
)

REM Comando padrão - mostrar ajuda
echo E-Language API - Comandos Disponíveis (Windows):
echo.
echo   commands.bat rebuild    - Rebuild completo (stop + compile + build + start)
echo   commands.bat quick      - Rebuild rápido (só backend)
echo   commands.bat compile    - Compilar projeto Maven
echo   commands.bat start      - Iniciar containers
echo   commands.bat stop       - Parar containers
echo   commands.bat logs       - Ver logs do backend
echo   commands.bat status     - Mostrar status
echo   commands.bat clean      - Limpar tudo
echo   commands.bat test       - Testar endpoint de criação
echo.
echo URLs importantes:
echo   Swagger UI: http://localhost:8080/swagger-ui/index.html
echo   API Docs:   http://localhost:8080/v3/api-docs
echo.
echo 💡 Dica: Use Git Bash para melhor compatibilidade com scripts .sh

:end