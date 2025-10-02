# 📚 Documentação Swagger Organizada

## 🎯 Objetivo
Este projeto utiliza uma abordagem organizada para documentação da API com Swagger, centralizando as anotações em classes dedicadas para evitar poluição visual nos controllers.

## 🏗️ Estrutura Organizada

### Antes (❌ Problemático)
```java
@PostMapping
@Operation(summary = "Criar nova unidade", description = "...")
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "..."),
    @ApiResponse(responseCode = "400", description = "..."),
    @ApiResponse(responseCode = "403", description = "...")
})
@RequestBody(description = "...", content = @Content(...))
@SecurityRequirement(name = "Bearer Authentication")
public ResponseEntity<String> create(@RequestBody UnidadeDto dto) {
    // Método perdido no meio das anotações
}
```

### Depois (✅ Organizado)
```java
@PostMapping
@UnidadeApiDocs.CreateUnidade
public ResponseEntity<String> create(@RequestBody UnidadeDto dto) {
    // Método limpo e focado na lógica
}
```

## 📁 Arquitetura da Documentação

### 1. Classes de Documentação
**Localização:** `com.tcc.e_language_api.web.docs`

- **UnidadeApiDocs.java** - Documentação para endpoints de unidades
- **IdiomaApiDocs.java** - Documentação para endpoints de idiomas  
- **PerfilApiDocs.java** - Documentação para endpoints de perfis

### 2. Anotações Customizadas
Cada classe de documentação contém anotações customizadas que encapsulam:

- ✅ **@Operation** - Título e descrição do endpoint
- ✅ **@ApiResponses** - Códigos de resposta e descrições
- ✅ **@RequestBody** - Documentação do body com exemplos
- ✅ **@SecurityRequirement** - Requisitos de autenticação
- ✅ **@ExampleObject** - Exemplos práticos de uso

### 3. Exemplo de Implementação

```java
// Classe de documentação
public class UnidadeApiDocs {
    
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Criar nova unidade", description = "...")
    @ApiResponses(value = {...})
    @RequestBody(examples = {...})
    @SecurityRequirement(name = "Bearer Authentication")
    public @interface CreateUnidade {}
}

// Controller limpo
@RestController
public class UnidadeController {
    
    @PostMapping
    @UnidadeApiDocs.CreateUnidade  // ← Uma única anotação!
    public ResponseEntity<String> create(@RequestBody UnidadeDto dto) {
        // Lógica do método
    }
}
```

## 🚀 Vantagens da Abordagem

### ✅ **Código Limpo**
- Controllers focados na lógica de negócio
- Redução drástica de linhas de código nos controllers
- Melhor legibilidade e manutenibilidade

### ✅ **Centralização**
- Documentação organizada em local específico
- Fácil localização e atualização
- Reutilização de padrões comuns

### ✅ **Exemplos Ricos**
- Exemplos práticos para cada endpoint
- Múltiplos cenários de uso
- Documentação interativa completa

### ✅ **Manutenibilidade**
- Mudanças na documentação em um só lugar
- Menor chance de inconsistências
- Facilita refatorações

## 🔧 Como Adicionar Nova Documentação

### 1. Criar nova anotação customizada:
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Seu título", description = "Sua descrição")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Sucesso"),
    @ApiResponse(responseCode = "400", description = "Erro")
})
public @interface SuaNovaAcao {}
```

### 2. Usar no controller:
```java
@PostMapping("/novo-endpoint")
@SuaClasseApiDocs.SuaNovaAcao
public ResponseEntity<?> novoEndpoint() {
    // Sua lógica
}
```

## 🎯 Padrões Estabelecidos

### Nomenclatura das Anotações:
- **CreateX** - Para endpoints de criação
- **GetById** - Para busca por ID
- **GetAll** - Para listagem completa
- **UpdateX** - Para atualizações (futuro)
- **DeleteX** - Para exclusões (futuro)

### Estrutura dos Exemplos:
- Sempre incluir exemplos práticos
- Usar dados realistas
- Cobrir cenários diferentes (básico, intermediário, avançado)

## 📊 Resultado Final

### Swagger UI Mantém Funcionalidade Completa:
- ✅ Documentação interativa
- ✅ Exemplos de request/response
- ✅ Autenticação JWT
- ✅ Códigos de erro detalhados
- ✅ Schemas de dados

### Controllers Mais Limpos:
- **Antes**: ~50 linhas por endpoint
- **Depois**: ~10 linhas por endpoint
- **Redução**: ~80% menos código visual

---

**💡 Dica:** Acesse http://localhost:8080/swagger-ui/index.html para ver a documentação em ação!