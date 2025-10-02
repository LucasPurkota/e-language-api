# 🌐 E-Language API

> API REST para plataforma de ensino de idiomas com Spring Boot 3 + PostgreSQL + Docker + Angular

## 📖 Documentação Completa

**🚀 Para começar a usar o projeto, consulte a documentação detalhada:**

### 👉 **[📚 GUIA COMPLETO - docs/README.md](./docs/README.md)**

A documentação completa inclui:
- ✅ **Pré-requisitos e instalação** (Windows/Linux/Mac)
- ✅ **Scripts de desenvolvimento** automatizados
- ✅ **Passo a passo** para rodar o projeto
- ✅ **Troubleshooting** para problemas comuns
- ✅ **Guia de colaboração** para novos desenvolvedores
- ✅ **Integração frontend-backend** com Angular

---

## ⚡ Quick Start

### **1. Pré-requisitos:**
- Java 17+ ☕
- Maven 3.8+ 📦
- Docker Desktop 🐳
- Git 🔧
- Node.js & npm (opcional para desenvolvimento frontend) 📱

### **2. Rodar projeto:**
```bash
# Clone o repositório
git clone https://github.com/LucasPurkota/e-language-api.git
cd e-language-api

# Windows: Use Git Bash ou veja docs/windows-quickstart.md
./commands.sh rebuild

# Aguarde e acesse:
# Backend: http://localhost:8080/swagger-ui/index.html
# Frontend: http://localhost:3000
```

### **3. URLs importantes:**
- 🌐 **Frontend Angular:** http://localhost:3000
- 🔧 **Backend API:** http://localhost:8080
- 📚 **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- 📋 **API Docs:** http://localhost:8080/v3/api-docs

---

## 🖥️ Mini Frontend Angular

O projeto inclui um frontend Angular minimalista para demonstrar a integração com a API REST:

### **Recursos do Frontend:**
- 📱 Interface simples e responsiva
- 🔄 CRUD completo de usuários
- 🛡️ Integração com autenticação JWT
- 🌐 Comunicação via proxy reverso com Nginx

### **Estrutura do Frontend:**
```
frontend/
├── src/app/
│   ├── models/          # DTOs TypeScript espelhando o backend
│   ├── services/        # Serviços HTTP para comunicação com API
│   ├── usuario-form/    # Componente para criação de usuários
│   └── usuario-list/    # Componente para listagem de usuários
├── nginx.conf          # Configuração do proxy reverso
└── Dockerfile          # Configuração para build em container
```

### **Funcionamento:**
- O frontend Angular é servido pelo Nginx na porta 3000
- Requisições para `/api/v1/*` são redirecionadas para o backend Spring Boot
- O frontend utiliza models TypeScript que espelham os DTOs do backend
- A comunicação é feita usando o HttpClient do Angular com `withCredentials: true`

---

## 🛠️ Boas Práticas de Desenvolvimento

### **1. Desenvolvimento Backend:**
```bash
# Inicie apenas o backend e banco para desenvolvimento mais rápido
./commands.sh start-back

# Após alterações no código Java
./commands.sh rebuild-back

# Verifique logs para erros
./commands.sh logs
```

### **2. Desenvolvimento Frontend:**
```bash
# Opção 1: Desenvolvimento local mais rápido (hot reload)
./commands.sh start-back  # Inicia backend + DB
cd frontend
npm install
npm start  # Acesse http://localhost:4200

# Opção 2: Desenvolvimento em container
./commands.sh rebuild-front
```

### **3. Testes de Integração:**
```bash
# Teste a comunicação entre frontend e backend
./commands.sh test-front

# Diagnostique problemas de proxy reverso
./commands.sh debug-proxy
```

### **4. Boas Práticas DTOs:**
O projeto separa os DTOs para entrada e saída:
```java
// Backend: DTOs separados para diferentes operações
public class UsuarioCreateRequest { ... }
public class UsuarioUpdateRequest { ... }
public class UsuarioResponse { ... }
public class UsuarioListResponse { ... }
```

```typescript
// Frontend: Models TypeScript que espelham os DTOs do backend
export interface UsuarioCreateRequest {
  nome: string;
  email: string;
  cpf: string;
  senha: string;
}

export interface UsuarioListResponse {
  usuarioId: string;
  nome: string;
  email: string;
  // ...outros campos
}
```

---

## 🚀 Scripts de Desenvolvimento

```bash
# Gerenciamento completo
./commands.sh rebuild        # Rebuild completo (backend + frontend)
./commands.sh start          # Iniciar todos os serviços
./commands.sh stop           # Parar todos os serviços
./commands.sh status         # Verificar estado dos serviços

# Desenvolvimento backend
./commands.sh rebuild-back   # Recompilar e reiniciar backend
./commands.sh logs           # Ver logs do backend

# Desenvolvimento frontend
./commands.sh rebuild-front  # Reconstruir e reiniciar frontend
./commands.sh logs-front     # Ver logs do frontend
./commands.sh dev-front      # Iniciar frontend em modo desenvolvimento local

# Diagnóstico
./commands.sh test-front     # Testar comunicação frontend-backend
./commands.sh debug-proxy    # Diagnóstico de problemas de proxy reverso
```

---

## 🏗️ Tecnologias

- **Backend:** Spring Boot 3.5.4
- **Database:** PostgreSQL 15
- **Security:** JWT Authentication
- **Documentation:** SpringDoc OpenAPI (Swagger)
- **Frontend:** Angular 19
- **UI Server:** Nginx
- **Build:** Maven (backend), npm (frontend)
- **Containers:** Docker + Docker Compose

---

## 📁 Estrutura

```
e-language-api/
├── 📄 README.md                 # Documentação principal
├── 📄 docker-compose.yml        # Configuração containers
├── 📄 commands.sh              # Scripts rápidos
├── 📁 docs/                    # 📚 Documentação detalhada
├── 📁 src/main/java/           # 💻 Código fonte backend
│   └── com/tcc/e_language_api/
│       ├── 📁 config/          # Configurações
│       ├── 📁 entity/          # Entidades JPA
│       ├── 📁 repository/      # Repositórios
│       ├── 📁 service/         # Lógica de negócio
│       ├── 📁 web/dto/         # DTOs Request/Response
│       └── 📁 jwt/             # Autenticação JWT
├── 📁 frontend/                # 🖥️ Código fonte frontend Angular
└── 📁 target/                  # Arquivos compilados
```

---

## 🤝 Colaboração

### **Fluxo para novos desenvolvedores:**

1. **Clone o projeto**
2. **Instale pré-requisitos** (Java, Maven, Docker)
3. **Execute rebuild:**
   - Git Bash: `./commands.sh rebuild`
   - Windows CMD/PowerShell: `commands.bat rebuild`
4. **Acesse:** 
   - Backend: http://localhost:8080/swagger-ui/index.html
   - Frontend: http://localhost:3000
5. **Comece a desenvolver!**

### **Workflow diário:**
1. `git pull origin main`
2. **Após mudanças no backend:**
   - `./commands.sh rebuild-back`
3. **Após mudanças no frontend:**
   - `./commands.sh rebuild-front`
4. **Testar integração:**
   - `./commands.sh test-front`
5. `git add .` → `git commit` → `git push`

---

## 📞 Suporte

**Se nada funcionar:**
1. Verifique se todos os pré-requisitos estão instalados
2. Execute `./commands.sh clean` e depois `./commands.sh rebuild`
3. Verifique logs com `./commands.sh logs` ou `./commands.sh logs-front`
4. Abra uma issue no GitHub com os logs de erro

**Problemas específicos de integração frontend-backend:**
1. Execute `./commands.sh debug-proxy` para diagnosticar problemas
2. Verifique a configuração CORS no arquivo `SpringSecurityConfig.java`
3. Confirme que o frontend está usando o parâmetro `withCredentials: true`

---

## 📄 Licença

Este projeto está sob licença MIT. Veja o arquivo [LICENSE](./LICENSE) para detalhes.

---

**⭐ Se este projeto foi útil, deixe uma estrela no GitHub!**