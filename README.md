# 🌐 E-Language API

> API REST para plataforma de ensino de idiomas com Spring Boot 3 + PostgreSQL + Docker

## 📖 Documentação Completa

**🚀 Para começar a usar o projeto, consulte a documentação detalhada:**

### 👉 **[📚 GUIA COMPLETO - docs/README.md](./docs/README.md)**

A documentação completa inclui:
- ✅ **Pré-requisitos e instalação** (Windows/Linux/Mac)
- ✅ **Scripts de desenvolvimento** automatizados
- ✅ **Passo a passo** para rodar o projeto
- ✅ **Troubleshooting** para problemas comuns
- ✅ **Guia de colaboração** para novos desenvolvedores

---

## ⚡ Quick Start

### **1. Pré-requisitos:**
- Java 17+ ☕
- Maven 3.8+ 📦
- Docker Desktop 🐳
- Git 🔧

### **2. Rodar projeto:**
```bash
# Clone o repositório
git clone https://github.com/LucasPurkota/e-language-api.git
cd e-language-api

# Windows: Use Git Bash ou veja docs/windows-quickstart.md
./commands.sh rebuild

# Aguarde e acesse:
# http://localhost:8080/swagger-ui/index.html
```

### **3. URLs importantes:**
- 🌐 **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- 📋 **API Docs:** http://localhost:8080/v3/api-docs
- 🗄️ **Database:** localhost:5432

---

## 🛠️ Scripts de Desenvolvimento

```bash
./commands.sh rebuild    # Rebuild completo (primeira vez)
./commands.sh quick      # Rebuild rápido (uso diário)
./commands.sh start      # Iniciar containers
./commands.sh stop       # Parar containers
./commands.sh status     # Ver status
./commands.sh logs       # Ver logs
./commands.sh clean      # Limpar tudo
```

---

## 🏗️ Tecnologias

- **Backend:** Spring Boot 3.5.4
- **Database:** PostgreSQL 15
- **Security:** JWT Authentication
- **Documentation:** SpringDoc OpenAPI (Swagger)
- **Build:** Maven
- **Containers:** Docker + Docker Compose

---

## 📁 Estrutura

```
📁 docs/              # 📚 Documentação completa
📁 src/main/java/     # 💻 Código fonte
📁 target/            # 🔨 Build artifacts
📄 docker-compose.yml # 🐳 Configuração containers
📄 commands.sh        # 🚀 Scripts de desenvolvimento
📄 pom.xml            # 📦 Dependências Maven
```

---

## 🤝 Colaboração

### **Para novos desenvolvedores:**
1. 📖 **Leia:** [docs/README.md](./docs/README.md) 
2. 🛠️ **Instale:** Java 17, Maven, Docker
3. 🚀 **Execute:** `./commands.sh rebuild`
4. 🎯 **Teste:** http://localhost:8080/swagger-ui/index.html

### **Workflow diário:**
```bash
git pull origin main
# ... fazer mudanças ...
./commands.sh quick     # Testar mudanças
./commands.sh test      # Validar API
git add . && git commit -m "feat: sua feature"
git push origin branch-name
```

---

## 📊 Status do Projeto

### ✅ **Implementado:**
- Autenticação JWT
- CRUD Usuários
- CRUD Idiomas  
- CRUD Unidades
- Sistema de Perfis
- Documentação Swagger
- Scripts de desenvolvimento

### 🔄 **Em desenvolvimento:**
- Sistema de Aulas
- Sistema de Questões
- Dashboard de progresso
- Testes automatizados

---

## 🎯 API Endpoints

### **Principais módulos:**
- 👤 **Usuários:** `/api/v1/usuarios`
- 🔐 **Autenticação:** `/api/v1/auth`  
- 🌍 **Idiomas:** `/api/v1/idioma`
- 📚 **Unidades:** `/api/v1/unidade`
- 🎭 **Perfis:** `/api/v1/perfil`

**Ver documentação completa no Swagger UI** 👆

---

## 📞 Suporte

### **Problemas? Soluções:**
1. 📖 Consulte [docs/README.md](./docs/README.md)
2. 🧹 Execute `./commands.sh clean && ./commands.sh rebuild`
3. 📋 Verifique logs com `./commands.sh logs`
4. 🐛 Abra uma issue no GitHub

### **Documentação adicional:**
- [🪟 Windows - Guia Rápido](./docs/windows-quickstart.md)
- [🐳 Docker Setup](./docs/docker.md)
- [🔌 API Examples](./docs/api.md)
- [⚙️ Local Setup](./docs/setup.md)

---

## 📄 Licença

Este projeto está sob licença MIT. Veja o arquivo [LICENSE](./LICENSE) para detalhes.

---

**⭐ Se este projeto foi útil, deixe uma estrela no GitHub!**