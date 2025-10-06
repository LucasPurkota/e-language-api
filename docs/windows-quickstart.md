# 🪟 Windows - Guia Rápido

## ⚡ Setup Inicial (10 minutos)

### **1. Instalar pré-requisitos:**
- ☕ **Java 17:** https://adoptium.net/
- 📦 **Maven:** https://maven.apache.org/download.cgi
- 🐳 **Docker Desktop:** https://www.docker.com/products/docker-desktop

### **2. Verificar instalação:**
```powershell
.\setup.ps1
```

### **3. Primeiro build:**
```cmd
commands.bat rebuild
```

### **4. Testar:**
- Abrir: http://localhost:8080/swagger-ui/index.html
- Ou executar: `commands.bat test`

---

## 🚀 Comandos Essenciais

| Ação | Git Bash | Windows CMD/PowerShell |
|------|----------|------------------------|
| **Rebuild completo** | `./commands.sh rebuild` | `commands.bat rebuild` |
| **Rebuild rápido** | `./commands.sh quick` | `commands.bat quick` |
| **Iniciar** | `./commands.sh start` | `commands.bat start` |
| **Parar** | `./commands.sh stop` | `commands.bat stop` |
| **Ver logs** | `./commands.sh logs` | `commands.bat logs` |
| **Status** | `./commands.sh status` | `commands.bat status` |
| **Testar API** | `./commands.sh test` | `commands.bat test` |
| **Limpar tudo** | `./commands.sh clean` | `commands.bat clean` |

---

## 🛠️ Workflow Diário

### **Manhã (iniciar trabalho):**
```cmd
git pull origin main
commands.bat quick
```

### **Durante desenvolvimento:**
```cmd
REM Após fazer mudanças no código
commands.bat quick

REM Ver se aplicação subiu
commands.bat status

REM Ver logs se der problema
commands.bat logs
```

### **Antes de fazer commit:**
```cmd
commands.bat test
git add .
git commit -m "Sua mensagem"
git push origin main
```

---

## 🚨 Problemas Comuns

### **"Comando não encontrado"**
- Use `commands.bat [comando]` no CMD/PowerShell
- Use `./commands.sh [comando]` no Git Bash

### **"Docker não responde"**
1. Verificar se Docker Desktop está executando
2. Restart Docker Desktop
3. `commands.bat clean` e depois `commands.bat rebuild`

### **"Permission denied"**
- Git Bash: `chmod +x commands.sh`
- Execute como Administrador se necessário

### **"Java não encontrado"**
1. Verificar se Java 17 está instalado
2. Executar `.\setup.ps1` para diagnosticar
3. Configurar JAVA_HOME se necessário

---

## 📱 URLs Importantes

- **🌐 Swagger:** http://localhost:8080/swagger-ui/index.html
- **📋 API Docs:** http://localhost:8080/v3/api-docs  
- **🔍 Health:** http://localhost:8080/actuator/health

---

## 💡 Dicas Windows

1. **Use Git Bash** para melhor compatibilidade com scripts
2. **Execute .\setup.ps1** sempre que tiver dúvidas sobre configuração
3. **Adicione projeto na exclusão do antivírus** para melhor performance
4. **Configure WSL2** no Docker Desktop para melhor performance

---

## 🆘 Suporte Rápido

**Se nada funcionar:**
```cmd
commands.bat clean
commands.bat rebuild
commands.bat status
```

**Se ainda não funcionar:**
1. Verifique pré-requisitos: `.\setup.ps1`
2. Verifique logs: `commands.bat logs`
3. Abra issue no GitHub com os logs