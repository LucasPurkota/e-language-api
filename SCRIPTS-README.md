# Scripts de Desenvolvimento - E-Language API

Este projeto contém dois scripts para facilitar o desenvolvimento:

## 🎯 commands.sh - Comandos Rápidos

Script para execução rápida de comandos específicos.

### Uso:
```bash
./commands.sh [comando]
```

### Comandos Disponíveis:

| Comando | Descrição |
|---------|-----------|
| `rebuild` | Rebuild completo (stop + compile + build + start) |
| `quick` | Rebuild rápido (apenas backend) |
| `compile` | Compilar projeto Maven |
| `start` | Iniciar containers |
| `stop` | Parar containers |
| `logs` | Ver logs do backend |
| `status` | Mostrar status dos containers e aplicação |
| `clean` | Limpar tudo (containers, volumes, imagens) |
| `test` | Testar endpoint de criação de usuário |

### Exemplos:
```bash
# Rebuild completo
./commands.sh rebuild

# Rebuild rápido (desenvolvimento)
./commands.sh quick

# Ver logs em tempo real
./commands.sh logs

# Verificar status
./commands.sh status
```

## 🎮 dev-script.sh - Menu Interativo

Script com menu interativo para desenvolvimento.

### Uso:
```bash
./dev-script.sh
```

Este script abre um menu com as mesmas opções do `commands.sh`, mas de forma interativa.

## 🚀 Fluxo de Desenvolvimento Recomendado

### Primeira execução:
```bash
./commands.sh rebuild
```

### Durante o desenvolvimento:
```bash
# Após fazer mudanças no código
./commands.sh quick

# Para ver logs
./commands.sh logs

# Para verificar se está funcionando
./commands.sh status
```

### Para limpar tudo e recomeçar:
```bash
./commands.sh clean
./commands.sh rebuild
```

## 📋 URLs Importantes

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **API Docs JSON**: http://localhost:8080/v3/api-docs
- **Aplicação**: http://localhost:8080

## 🔧 Pré-requisitos

- Docker e Docker Compose instalados
- Maven instalado
- Bash shell

## 📝 Notas

- O comando `quick` é mais rápido para desenvolvimento pois não para o banco de dados
- O comando `rebuild` é mais seguro para garantir que tudo está atualizado
- Use `clean` apenas quando necessário, pois remove todos os dados do banco