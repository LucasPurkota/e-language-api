# E-Language API - Script de Setup para Windows
# Execute no PowerShell: .\setup.ps1

Write-Host "🚀 E-Language API - Setup para Windows" -ForegroundColor Blue
Write-Host "=====================================" -ForegroundColor Blue

# Função para verificar se um comando existe
function Test-Command {
    param($Command)
    try {
        Get-Command $Command -ErrorAction Stop
        return $true
    } catch {
        return $false
    }
}

# Função para imprimir status
function Write-Status {
    param($Message, $Status)
    if ($Status) {
        Write-Host "✅ $Message" -ForegroundColor Green
    } else {
        Write-Host "❌ $Message" -ForegroundColor Red
    }
}

Write-Host "`n🔍 Verificando pré-requisitos..." -ForegroundColor Yellow

# Verificar Java
$javaInstalled = Test-Command "java"
Write-Status "Java 17+" $javaInstalled
if ($javaInstalled) {
    $javaVersion = java -version 2>&1 | Select-String "version" | Select-Object -First 1
    Write-Host "   Versão: $javaVersion" -ForegroundColor Gray
}

# Verificar Maven
$mavenInstalled = Test-Command "mvn"
Write-Status "Maven 3.8+" $mavenInstalled
if ($mavenInstalled) {
    $mavenVersion = mvn -version | Select-String "Apache Maven" | Select-Object -First 1
    Write-Host "   Versão: $mavenVersion" -ForegroundColor Gray
}

# Verificar Docker
$dockerInstalled = Test-Command "docker"
Write-Status "Docker" $dockerInstalled
if ($dockerInstalled) {
    $dockerVersion = docker --version
    Write-Host "   Versão: $dockerVersion" -ForegroundColor Gray
}

# Verificar Docker Compose
$composeInstalled = Test-Command "docker-compose"
Write-Status "Docker Compose" $composeInstalled
if ($composeInstalled) {
    $composeVersion = docker-compose --version
    Write-Host "   Versão: $composeVersion" -ForegroundColor Gray
}

# Verificar Git
$gitInstalled = Test-Command "git"
Write-Status "Git" $gitInstalled
if ($gitInstalled) {
    $gitVersion = git --version
    Write-Host "   Versão: $gitVersion" -ForegroundColor Gray
}

Write-Host "`n📋 Resumo:" -ForegroundColor Yellow

$allInstalled = $javaInstalled -and $mavenInstalled -and $dockerInstalled -and $composeInstalled -and $gitInstalled

if ($allInstalled) {
    Write-Host "🎉 Todos os pré-requisitos estão instalados!" -ForegroundColor Green
    Write-Host "`n🚀 Próximos passos:" -ForegroundColor Blue
    Write-Host "1. Abra Git Bash no diretório do projeto" -ForegroundColor White
    Write-Host "2. Execute: ./commands.sh rebuild" -ForegroundColor White
    Write-Host "3. Aguarde e acesse: http://localhost:8080/swagger-ui/index.html" -ForegroundColor White
    Write-Host "`n💡 Ou use PowerShell/CMD:" -ForegroundColor Blue
    Write-Host "   commands.bat rebuild" -ForegroundColor White
} else {
    Write-Host "⚠️  Alguns pré-requisitos estão faltando!" -ForegroundColor Yellow
    Write-Host "`n📥 Downloads necessários:" -ForegroundColor Blue
    
    if (-not $javaInstalled) {
        Write-Host "• Java 17: https://adoptium.net/temurin/releases/" -ForegroundColor White
    }
    if (-not $mavenInstalled) {
        Write-Host "• Maven: https://maven.apache.org/download.cgi" -ForegroundColor White
    }
    if (-not $dockerInstalled) {
        Write-Host "• Docker Desktop: https://www.docker.com/products/docker-desktop" -ForegroundColor White
    }
    if (-not $gitInstalled) {
        Write-Host "• Git: https://git-scm.com/download/win" -ForegroundColor White
    }
    
    Write-Host "`n📖 Consulte docs/README.md para instruções detalhadas" -ForegroundColor Cyan
}

Write-Host "`n🔗 Links úteis:" -ForegroundColor Blue
Write-Host "• Documentação: docs/README.md" -ForegroundColor White
Write-Host "• Swagger UI: http://localhost:8080/swagger-ui/index.html" -ForegroundColor White
Write-Host "• Repository: https://github.com/LucasPurkota/e-language-api" -ForegroundColor White

Write-Host "`nPressione Enter para continuar..." -ForegroundColor Gray
Read-Host