# 🚀 E-Language API - Guia de Desenvolvimento

## 📋 Índice
1. [Pré-requisitos](#pré-requisitos)
2. [Instalação no Windows](#instalação-no-windows)
3. [Como rodar o projeto](#como-rodar-o-projeto)
4. [Scripts de desenvolvimento](#scripts-de-desenvolvimento)
5. [Testando a API](#testando-a-api)
6. [Estrutura do projeto](#estrutura-do-projeto)
7. [Troubleshooting](#troubleshooting)

---

## 🔧 Pré-requisitos

### **Software Necessário:**

#### **1. Docker Desktop** ⭐ (Obrigatório)
- **Download:** https://www.docker.com/products/docker-desktop
- **Versão:** 4.0 ou superior
- **Por que:** Roda PostgreSQL e a aplicação em containers

#### **2. Git** ⭐ (Obrigatório)
- **Download:** https://git-scm.com/download/win
- **Versão:** 2.30 ou superior
- **Por que:** Controle de versão

#### **3. Java 17** ⭐ (Obrigatório)
- **Download:** https://adoptium.net/temurin/releases/
- **Versão:** OpenJDK 17 LTS
- **Por que:** Runtime da aplicação Spring Boot

#### **4. Maven** ⭐ (Obrigatório)
- **Download:** https://maven.apache.org/download.cgi
- **Versão:** 3.8 ou superior
- **Por que:** Gerenciamento de dependências e build

#### **5. Git Bash** 🔥 (Altamente Recomendado)
- **Instalado com Git**
- **Por que:** Executa scripts `.sh` no Windows

#### **6. VS Code** 💡 (Recomendado)
- **Download:** https://code.visualstudio.com/
- **Extensões úteis:**
  - Java Extension Pack
  - Spring Boot Extension Pack
  - Docker
  - REST Client

#### **7. Postman** 💡 (Opcional)
- **Download:** https://www.postman.com/downloads/
- **Por que:** Testar endpoints da API

---

## 🪟 Instalação no Windows

### **Passo 1: Instalar Docker Desktop**
1. Baixe e instale o Docker Desktop
2. Reinicie o computador
3. Abra o Docker Desktop e faça login
4. **Teste:** Abra PowerShell e execute:
   ```powershell
   docker --version
   docker-compose --version
   ```

### **Passo 2: Instalar Java 17**
1. Baixe o OpenJDK 17 da Adoptium
2. Instale seguindo o wizard
3. **Configure JAVA_HOME:**
   - Pressione `Win + R`, digite `sysdm.cpl`
   - Vá em "Avançado" → "Variáveis de Ambiente"
   - Adicione `JAVA_HOME` apontando para pasta do Java
   - Adicione `%JAVA_HOME%\bin` no PATH
4. **Teste:** Abra cmd e execute:
   ```cmd
   java -version
   javac -version
   ```

### **Passo 3: Instalar Maven**
1. Baixe o Maven (arquivo .zip)
2. Extraia para `C:\apache-maven-3.x.x`
3. **Configure variáveis:**
   - Adicione `MAVEN_HOME` → `C:\apache-maven-3.x.x`
   - Adicione `%MAVEN_HOME%\bin` no PATH
4. **Teste:**
   ```cmd
   mvn -version
   ```

### **Passo 4: Instalar Git**
1. Baixe e instale o Git for Windows
2. **Durante instalação, marque:**
   - Git Bash Here
   - Git from command line and 3rd-party software
3. **Teste:**
   ```cmd
   git --version
   ```

---

## 🚀 Como rodar o projeto

### **Método 1: Script Automático (Recomendado)**

#### **1. Clone o repositório:**
```bash
git clone https://github.com/LucasPurkota/e-language-api.git
cd e-language-api
```

#### **2. Abra Git Bash no diretório do projeto:**
- Clique com botão direito na pasta → "Git Bash Here"
- Ou abra Git Bash e navegue: `cd /c/caminho/para/e-language-api`

#### **3. Execute o script de setup:**
```bash
# Torna o script executável
chmod +x commands.sh

# Rebuild completo (primeira vez)
./commands.sh rebuild
```

#### **4. Aguarde a inicialização:**
- O script vai compilar, buildar e iniciar tudo
- Aguarde ver a mensagem: "✅ Rebuild completo finalizado!"

#### **5. Teste se funcionou:**
- Abra: http://localhost:8080/swagger-ui/index.html
- Deve aparecer a interface do Swagger

### **Método 2: Passo a Passo Manual**

#### **1. Compile o projeto:**
```bash
mvn clean package -DskipTests
```

#### **2. Inicie os containers:**
```bash
docker-compose up --build -d
```

#### **3. Verifique se subiu:**
```bash
docker-compose ps
```

---

## 🛠️ Scripts de desenvolvimento

## 🛠️ Scripts de desenvolvimento

### **🪟 Para usuários Windows:**

#### **Opção 1: Git Bash (Recomendado)**
```bash
# Torna executável
chmod +x commands.sh

# Comandos de Build & Deploy
./commands.sh rebuild          # Rebuild completo (backend + frontend)
./commands.sh rebuild-back     # Rebuild apenas do backend
./commands.sh rebuild-front    # Rebuild apenas do frontend
./commands.sh compile          # Compilar projeto Maven

# Comandos de Controle
./commands.sh start            # Iniciar todos os containers
./commands.sh start-back       # Iniciar backend + database
./commands.sh start-front      # Iniciar apenas frontend
./commands.sh stop             # Parar todos os containers
./commands.sh stop-back        # Parar apenas backend
./commands.sh stop-front       # Parar apenas frontend

# Comandos de Monitoramento
./commands.sh status           # Ver status de todos serviços
./commands.sh logs             # Ver logs do backend
./commands.sh logs-front       # Ver logs do frontend
./commands.sh logs-all         # Ver logs de todos serviços

# Comandos de Teste
./commands.sh test             # Testar API (endpoint de usuários)
./commands.sh test-front       # Testar conexão frontend-backend
./commands.sh debug-proxy      # Debugar configuração de proxy reverso

# Comandos Diversos
./commands.sh clean            # Limpar todos containers e volumes
./commands.sh dev-front        # Rodar frontend em modo desenvolvimento
```

#### **Opção 2: PowerShell/CMD**
```cmd
REM Verificar pré-requisitos
.\setup.ps1

REM Comandos de Build & Deploy
commands.bat rebuild          REM Rebuild completo (backend + frontend)
commands.bat rebuild-back     REM Rebuild apenas do backend
commands.bat rebuild-front    REM Rebuild apenas do frontend
commands.bat compile          REM Compilar projeto Maven

REM Comandos de Controle
commands.bat start            REM Iniciar todos os containers
commands.bat start-back       REM Iniciar backend + database
commands.bat start-front      REM Iniciar apenas frontend
commands.bat stop             REM Parar todos os containers
commands.bat stop-back        REM Parar apenas backend
commands.bat stop-front       REM Parar apenas frontend

REM Comandos de Monitoramento
commands.bat status           REM Ver status de todos serviços
commands.bat logs             REM Ver logs do backend
commands.bat logs-front       REM Ver logs do frontend
commands.bat logs-all         REM Ver logs de todos serviços

REM Comandos de Teste
commands.bat test             REM Testar API (endpoint de usuários)
commands.bat test-front       REM Testar conexão frontend-backend
commands.bat debug-proxy      REM Debugar configuração de proxy reverso

REM Comandos Diversos
commands.bat clean            REM Limpar todos containers e volumes
commands.bat dev-front        REM Rodar frontend em modo desenvolvimento
```

#### **Opção 3: Script Interativo**
```bash
# Menu interativo com todas as opções
./dev-script.sh
```

### **🐧 Para usuários Linux/Mac:**

```bash
# Mesmo que Git Bash no Windows
./commands.sh [comando]
./dev-script.sh  # Menu interativo
```

### **🔥 Fluxo de trabalho diário:**

#### **Primeira vez rodando:**
```bash
# Build completo da aplicação (backend + frontend + banco)
./commands.sh rebuild
```

#### **Desenvolvimento Backend:**
```bash
# Depois de fazer mudanças no código backend:
./commands.sh rebuild-back

# Para ver se está funcionando:
./commands.sh status

# Para ver logs do backend:
./commands.sh logs
```

#### **Desenvolvimento Frontend:**
```bash
# Modo 1: Desenvolvimento em contêiner (recomendado para teste integrado)
./commands.sh rebuild-front  # Após alterações no código Angular

# Modo 2: Desenvolvimento local (mais rápido para iterações)
./commands.sh start-back     # Inicia apenas o backend
cd frontend
npm start                   # Inicia o servidor de desenvolvimento Angular
# Acesse: http://localhost:4200

# Para ver logs do frontend:
./commands.sh logs-front
```

#### **Testando a integração:**
```bash
# Testa a comunicação entre frontend e backend
./commands.sh test-front

# Se houver problemas com o proxy reverso:
./commands.sh debug-proxy
```

#### **Quando der problema:**
```bash
# Limpa tudo e recomeça:
./commands.sh clean
./commands.sh rebuild
```

---

## 🧪 Testando a API

### **1. Swagger UI (Recomendado)**
- **URL:** http://localhost:8080/swagger-ui/index.html
- **O que é:** Interface visual para testar todos os endpoints
- **Como usar:**
  1. Abra a URL no navegador
  2. Clique em um endpoint para expandir
  3. Clique em "Try it out"
  4. Preencha os dados e clique "Execute"

### **2. Postman**
- Importe a collection: `docs/e_language_api API.postman_collection.json`

### **3. cURL (Git Bash)**

#### **Criar um usuário:**
```bash
curl -X POST http://localhost:8080/api/v1/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "cpf": "12345678901",
    "email": "joao@email.com",
    "senha": "MinhaSenh@123",
    "enderecos": [{
      "tipo": "RESIDENCIAL",
      "cep": "12345-678",
      "logradouro": "Rua Exemplo",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "uf": "SP",
      "pais": "Brasil",
      "numero": 123
    }]
  }'
```

#### **Fazer login:**
```bash
curl -X POST http://localhost:8080/api/v1/auth \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "senha": "MinhaSenh@123"
  }'
```

---

## 📁 Estrutura do projeto

```
e-language-api/
├── 📄 README.md                 # Documentação principal
├── 📄 docker-compose.yml        # Configuração containers
├── 📄 Dockerfile               # Build da aplicação
├── 📄 pom.xml                  # Dependências Maven
├── 📄 commands.sh              # Scripts rápidos
├── 📄 dev-script.sh            # Script interativo
├── 📁 docs/                    # 📚 Documentação detalhada
│   ├── 📄 README.md            # ← Este arquivo
│   ├── 📄 api.md               # Exemplos de API
│   ├── 📄 docker.md            # Guia Docker
│   └── 📄 setup.md             # Setup local
├── 📁 src/main/java/           # 💻 Código fonte
│   └── com/tcc/e_language_api/
│       ├── 📁 config/          # Configurações
│       ├── 📁 entity/          # Entidades JPA
│       ├── 📁 repository/      # Repositórios
│       ├── 📁 service/         # Lógica de negócio
│       ├── 📁 web/             # Controllers e DTOs
│       └── 📁 jwt/             # Autenticação JWT
└── 📁 target/                  # Arquivos compilados
```

---

## 🔧 Troubleshooting

### **❌ Problema: "Docker não encontrado"**
**Solução:**
1. Verifique se Docker Desktop está rodando
2. Reinicie o Docker Desktop
3. Teste: `docker --version`

### **❌ Problema: "Java não encontrado"**
**Solução:**
1. Verifique JAVA_HOME: `echo $JAVA_HOME`
2. Verifique PATH: `java -version`
3. Reinstale Java 17 se necessário

### **❌ Problema: "Maven não encontrado"**
**Solução:**
1. Verifique MAVEN_HOME: `echo $MAVEN_HOME`
2. Verifique PATH: `mvn -version`
3. Configure variáveis de ambiente

### **❌ Problema: "Porta 8080 em uso"**
**Solução:**
```bash
# Parar containers
./commands.sh stop

# Matar processo na porta 8080
netstat -ano | findstr :8080
taskkill /PID [número_do_pid] /F
```

### **❌ Problema: "Permissão negada scripts"**
**Solução Git Bash:**
```bash
chmod +x commands.sh
chmod +x dev-script.sh
```

### **❌ Problema: "Container não sobe"**
**Solução:**
```bash
# Limpar tudo e recomeçar
./commands.sh clean        # Git Bash
commands.bat clean         # Windows CMD/PowerShell
./commands.sh rebuild      # Git Bash  
commands.bat rebuild       # Windows CMD/PowerShell

# Ver logs de erro
./commands.sh logs         # Git Bash
commands.bat logs          # Windows CMD/PowerShell
```

### **❌ Problema: "Swagger não carrega"**
**Soluções:**
1. Aguarde 2-3 minutos após rebuild
2. Verifique se aplicação subiu: `commands.bat status` (Windows) ou `./commands.sh status` (Git Bash)
3. Verifique logs: `commands.bat logs` (Windows) ou `./commands.sh logs` (Git Bash)
4. Teste URL direta: http://localhost:8080/v3/api-docs

### **❌ Problema: "Erro de compilação Maven"**
**Solução:**
```bash
# Limpar cache Maven
mvn clean

# Forçar download de dependências
mvn dependency:resolve

# Compilar novamente
./commands.sh compile      # Git Bash
commands.bat compile       # Windows CMD/PowerShell
```

### **❌ Problema: "Comando não encontrado" (Windows)**
**Soluções:**
1. **PowerShell/CMD:** Use `commands.bat [comando]` em vez de `./commands.sh`
2. **Git Bash:** Torne executável primeiro: `chmod +x commands.sh`
3. **Verificar setup:** Execute `.\setup.ps1` no PowerShell

### **❌ Problema: "Permission denied" (Windows)**
**Soluções:**
```bash
# No Git Bash - dar permissão
chmod +x commands.sh
chmod +x dev-script.sh

# Se não resolver, execute Git Bash como Administrador
```

### **❌ Problema: Docker não responde (Windows)**
**Soluções:**
1. Verificar se Docker Desktop está executando
2. Restart Docker Desktop
3. Verificar portas ocupadas:
```cmd
netstat -ano | findstr :8080
netstat -ano | findstr :5432
taskkill /PID [numero_do_pid] /F
```

---

## 🎯 URLs Importantes

- **🌐 Frontend Angular:** http://localhost:3000
- **🔧 Backend API:** http://localhost:8080
- **📚 Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **📋 API Docs:** http://localhost:8080/v3/api-docs
- **🔍 Health Check:** http://localhost:8080/actuator/health
- **🗄️ Database:** localhost:5432 (user: asdf, pass: asdgf)
- **👨‍💻 Angular Dev Server:** http://localhost:4200 (quando rodando `npm start`)

---

## 💡 Dicas para Windows

### **1. Escolha da ferramenta de linha de comando:**
- **Git Bash (Recomendado):** Use scripts `.sh` - `./commands.sh [comando]`
- **PowerShell/CMD:** Use scripts `.bat` - `commands.bat [comando]`
- **Verificação de setup:** Execute `.\setup.ps1` no PowerShell

### **2. Configurar variáveis de ambiente:**
- Use interface gráfica: `sysdm.cpl` → Variáveis de Ambiente
- Ou execute `.\setup.ps1` para verificar configurações
- Teste sempre após configurar: `echo $JAVA_HOME` (Git Bash) ou `$env:JAVA_HOME` (PowerShell)

### **3. Problemas de permissão:**
- **Git Bash:** Use `chmod +x commands.sh` para tornar executável
- Execute Git Bash como administrador se necessário
- **PowerShell:** Pode precisar alterar ExecutionPolicy: `Set-ExecutionPolicy RemoteSigned`

### **4. Docker no Windows:**
- Certifique-se que Hyper-V está habilitado
- WSL2 é recomendado para melhor performance
- Docker Desktop deve estar executando antes de usar os scripts

### **5. Antivírus:**
- Adicione pasta do projeto na exclusão do antivírus
- Docker pode ser bloqueado por alguns antivírus
- Maven/Java podem ser afetados por antivírus em tempo real

### **6. Scripts disponíveis:**
```bash
# Git Bash (Linux-style)
./commands.sh [comando]
./dev-script.sh          # Menu interativo

# Windows CMD/PowerShell
commands.bat [comando]
.\setup.ps1              # Verificar pré-requisitos
```

---

## 🤝 Colaboração

### **Fluxo para novos desenvolvedores:**

1. **Clone o projeto**
2. **Instale pré-requisitos** (Java, Maven, Docker)
3. **Windows:** Execute `.\setup.ps1` para verificar instalações
4. **Execute rebuild:**
   - Git Bash: `./commands.sh rebuild`
   - Windows CMD/PowerShell: `commands.bat rebuild`
5. **Acesse:** http://localhost:8080/swagger-ui/index.html
6. **Comece a desenvolver!**

### **Workflow diário:**
1. `git pull origin main`
2. **Após mudanças, rebuild rápido:**
   - Git Bash: `./commands.sh quick`
   - Windows CMD/PowerShell: `commands.bat quick`
3. Desenvolver e testar
4. `git add .` → `git commit` → `git push`

### **Antes de fazer push:**
1. **Testar se funciona:**
   - Git Bash: `./commands.sh test`
   - Windows CMD/PowerShell: `commands.bat test`
2. **Verificar status:**
   - Git Bash: `./commands.sh status`
   - Windows CMD/PowerShell: `commands.bat status`

---

## 📞 Suporte

**Se nada funcionar:**
1. Verifique se todos os pré-requisitos estão instalados
2. Execute `./commands.sh clean` e depois `./commands.sh rebuild`
3. Verifique logs com `./commands.sh logs`
4. Abra uma issue no GitHub com os logs de erro

**Documentações adicionais:**
- [Docker Setup](./docker.md)
- [API Examples](./api.md)
- [Local Setup](./setup.md)