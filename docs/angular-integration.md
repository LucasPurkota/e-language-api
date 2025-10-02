# 🔌 Guia de Integração: Angular com Spring Boot

Este guia explica como integrar um frontend Angular com o backend Spring Boot da E-Language API, usando Docker e Nginx como proxy reverso.

## 📋 Índice
1. [Arquitetura da Solução](#arquitetura-da-solução)
2. [Configuração do Backend (Spring Boot)](#configuração-do-backend-spring-boot)
3. [Configuração do Frontend (Angular)](#configuração-do-frontend-angular)
4. [Configuração do Nginx como Proxy Reverso](#configuração-do-nginx-como-proxy-reverso)
5. [Docker e Docker Compose](#docker-e-docker-compose)
6. [Fluxo de Desenvolvimento](#fluxo-de-desenvolvimento)
7. [Troubleshooting](#troubleshooting)

---

## 🏗️ Arquitetura da Solução

A arquitetura da aplicação é composta por três componentes principais:

1. **Frontend Angular**: Aplicação SPA (Single Page Application) servida pelo Nginx na porta 3000
2. **Backend Spring Boot**: API REST na porta 8080
3. **Banco de Dados PostgreSQL**: Banco de dados na porta 5432

O Nginx atua como servidor web para o frontend Angular e como proxy reverso para redirecionar chamadas à API para o backend Spring Boot.

```

Cliente Web -> [Nginx (porta 3000)] -> [Spring Boot (porta 8080)] -> [PostgreSQL (porta 5432)]
                      ^                          ^
                      |                          |
                Frontend Angular           API REST (Backend)
```

### Fluxo de Comunicação:
1. O navegador acessa `http://localhost:3000` para o frontend Angular
2. As requisições à API (`/api/v1/*`) são redirecionadas pelo Nginx para `http://backend:8080/api/v1/*`
3. O backend processa as requisições e se comunica com o PostgreSQL

---

## 🔧 Configuração do Backend (Spring Boot)

### 1. Configuração CORS no Spring Security

A configuração CORS é essencial para permitir que o frontend acesse o backend. No arquivo `SpringSecurityConfig.java`:

```java
@Bean
public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
    org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
    // Permitir origens específicas (frontend)
    configuration.setAllowedOriginPatterns(java.util.Arrays.asList(
        "http://localhost:4200", "http://127.0.0.1:4200",  // Angular dev server
        "http://localhost:3000", "http://127.0.0.1:3000"   // Nginx container
    ));
    // Métodos HTTP permitidos
    configuration.setAllowedMethods(java.util.Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
    // Headers permitidos
    configuration.setAllowedHeaders(java.util.Arrays.asList("*"));
    // Permitir credenciais (cookies, autenticação)
    configuration.setAllowCredentials(true);
    
    org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
```

### 2. Configuração do SecurityFilterChain

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // outras configurações...
            .build();
}
```

### 3. Endpoints da API

Todos os endpoints da API devem seguir um padrão RESTful:
- Base URL: `/api/v1`
- Recursos: `/api/v1/usuarios`, `/api/v1/auth`, etc.
- Métodos HTTP: GET, POST, PUT, DELETE, PATCH

---

## 💻 Configuração do Frontend (Angular)

### 1. Estrutura do Projeto Angular

```
frontend/
├── src/
│   ├── app/
│   │   ├── models/              # Interfaces/tipos para objetos do domínio
│   │   ├── services/            # Serviços para comunicação com a API
│   │   └── components/          # Componentes da interface
│   └── environments/
│       ├── environment.ts       # Configurações para ambiente de desenvolvimento
│       └── environment.prod.ts  # Configurações para ambiente de produção
├── angular.json                 # Configuração do Angular CLI
└── Dockerfile                   # Build do frontend para produção
```

### 2. Configuração de Ambiente

Arquivo `environment.ts` para desenvolvimento local:
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api/v1' // Acessando diretamente o backend
};
```

Arquivo `environment.prod.ts` para produção (Docker):
```typescript
export const environment = {
  production: true,
  apiUrl: '/api/v1' // Usando o proxy reverso do Nginx
};
```

### 3. Serviço de Comunicação com a API

Exemplo de serviço para comunicação com a API (aqui usando o recurso de usuários):

```typescript
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = `${environment.apiUrl}/usuarios`;
  
  // Importante: withCredentials: true permite enviar cookies/tokens entre origens diferentes
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    withCredentials: true
  };

  constructor(private http: HttpClient) {}

  create(usuario: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, usuario, this.httpOptions);
  }

  // Outros métodos: update, delete, getById, getAll, etc.
}
```

### 4. Configuração do HttpClientModule

No arquivo `app.module.ts` ou `app.config.ts` (Angular 17+):

```typescript
// Para Angular < 17
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    HttpClientModule
    // outros módulos...
  ],
  // ...
})
export class AppModule { }

// Para Angular 17+
import { ApplicationConfig } from '@angular/core';
import { provideHttpClient, withInterceptors } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(withInterceptors([
      // seus interceptors, se necessário
    ]))
  ]
};
```

### 5. Componente de Exemplo (Formulário)

```typescript
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <form (ngSubmit)="onSubmit()">
      <div>
        <label for="nome">Nome</label>
        <input type="text" id="nome" [(ngModel)]="usuario.nome" name="nome" required>
      </div>
      
      <div>
        <label for="email">Email</label>
        <input type="email" id="email" [(ngModel)]="usuario.email" name="email" required>
      </div>
      
      <div>
        <label for="senha">Senha</label>
        <input type="password" id="senha" [(ngModel)]="usuario.senha" name="senha" required>
      </div>
      
      <button type="submit">Salvar</button>
    </form>
    
    <div *ngIf="mensagem" [class]="sucesso ? 'sucesso' : 'erro'">
      {{ mensagem }}
    </div>
  `
})
export class UsuarioFormComponent {
  usuario = {
    nome: '',
    email: '',
    senha: ''
  };
  
  mensagem = '';
  sucesso = false;
  
  constructor(private usuarioService: UsuarioService) {}
  
  onSubmit(): void {
    this.usuarioService.create(this.usuario).subscribe({
      next: (response) => {
        this.mensagem = 'Usuário criado com sucesso!';
        this.sucesso = true;
        this.usuario = { nome: '', email: '', senha: '' };
      },
      error: (error) => {
        this.mensagem = `Erro ao criar usuário: ${error.message}`;
        this.sucesso = false;
      }
    });
  }
}
```

---

## 🌐 Configuração do Nginx como Proxy Reverso

O Nginx serve duas funções:
1. Servidor web para o frontend Angular
2. Proxy reverso para as requisições à API

### nginx.conf:

```conf
server {
    listen 80;
    server_name localhost;
    root /usr/share/nginx/html;
    index index.html;

    # Configuração para SPA - redirecionar todas as rotas para index.html
    location / {
        try_files $uri $uri/ /index.html;
    }

    # Cache para assets estáticos
    location ~* \.(js|css|png|jpg|jpeg|gif|ico|svg)$ {
        expires 1y;
        add_header Cache-Control "public, immutable";
    }

    # Proxy reverso para o backend
    location /api/v1/ {
        proxy_pass http://backend:8080/api/v1/;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        
        # Configuração de CORS
        add_header 'Access-Control-Allow-Origin' '*' always;
        add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS, PUT, DELETE' always;
        add_header 'Access-Control-Allow-Headers' 'DNT,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range,Authorization' always;
        
        if ($request_method = 'OPTIONS') {
            add_header 'Access-Control-Max-Age' 1728000;
            add_header 'Content-Type' 'text/plain; charset=utf-8';
            add_header 'Content-Length' 0;
            return 204;
        }
    }

    # Logs
    error_log /var/log/nginx/error.log;
    access_log /var/log/nginx/access.log;
}
```

---

## 🐳 Docker e Docker Compose

### Dockerfile para o Frontend

```dockerfile
# Estágio de build
FROM node:20 as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Estágio de produção
FROM nginx:alpine
COPY --from=build /app/dist/frontend/browser/ /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

### Docker Compose

```yml
version: '3.8'

networks:
  e-language-network:
    driver: bridge

services:
  db:
    image: postgres:15
    container_name: e-language-db
    environment:
      POSTGRES_DB: e_language
      POSTGRES_USER: asdf
      POSTGRES_PASSWORD: asdgf
    ports:
      - "5432:5432"
    volumes:
      - db_data:/var/lib/postgresql/data
    networks:
      - e-language-network

  backend:
    build: .
    container_name: e-language-backend
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/e_language
      SPRING_DATASOURCE_USERNAME: asdf
      SPRING_DATASOURCE_PASSWORD: asdgf
    ports:
      - "8080:8080"
    restart: always
    networks:
      - e-language-network

  mini-frontend:
    build: 
      context: ./frontend
      dockerfile: Dockerfile
    container_name: e-language-frontend
    ports:
      - "3000:80"
    depends_on:
      - backend
    restart: always
    environment:
      - NODE_ENV=production
    networks:
      - e-language-network

volumes:
  db_data:
```

---

## 🚀 Fluxo de Desenvolvimento

### 1. Desenvolvimento Local

Para desenvolvimento local, você pode executar o Angular separadamente:

```bash
# Terminal 1: Rodar o backend
./commands.sh start-back

# Terminal 2: Rodar o frontend em modo de desenvolvimento
cd frontend
npm install
npm start
# Acesso: http://localhost:4200
```

### 2. Deploy com Docker

Para deploy usando Docker:

```bash
# Rebuild completo (backend + frontend)
./commands.sh rebuild

# Ou apenas o frontend
./commands.sh rebuild-front

# Acesso:
# Frontend: http://localhost:3000
# Backend: http://localhost:8080
```

---

## 🔍 Troubleshooting

### 1. Problemas de CORS

**Problema**: Erro "Access to XMLHttpRequest has been blocked by CORS policy"

**Soluções**:
1. Verifique a configuração CORS no `SpringSecurityConfig.java`
2. Certifique-se de que `withCredentials: true` está definido nos serviços Angular
3. Confirme que as origens permitidas incluem `http://localhost:3000` e `http://localhost:4200`

### 2. Erro 403 Forbidden

**Problema**: Requisições recebem erro 403 Forbidden

**Soluções**:
1. Verifique se a rota está configurada para permitir acesso público no SecurityConfig
2. Certifique-se de que está enviando os tokens de autenticação corretamente
3. Verifique logs do Spring para mensagens de erro específicas

### 3. Requisições API não chegam ao Backend

**Problema**: Requisições à API não são recebidas pelo backend

**Soluções**:
1. Teste o proxy reverso usando o comando:
   ```bash
   ./commands.sh debug-proxy
   ```
2. Verifique os logs do Nginx:
   ```bash
   docker exec -it e-language-frontend tail /var/log/nginx/error.log
   ```
3. Confirme que a URL da API está correta em `environment.ts`

### 4. Autenticação com JWT

**Problema**: Token JWT não é enviado ou reconhecido

**Soluções**:
1. Adicione um interceptor HTTP para adicionar o token JWT automaticamente:
   ```typescript
   @Injectable()
   export class JwtInterceptor implements HttpInterceptor {
     intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       const token = localStorage.getItem('token');
       if (token) {
         const cloned = req.clone({
           headers: req.headers.set('Authorization', `Bearer ${token}`)
         });
         return next.handle(cloned);
       }
       return next.handle(req);
     }
   }
   ```
2. Certifique-se de que o token está sendo armazenado após o login

### 5. Testando a Comunicação

Use o comando de teste para verificar se a comunicação está funcionando:

```bash
./commands.sh test-front
```

Isso verifica:
- Se o frontend está acessível
- Se o backend está acessível diretamente
- Se a API está acessível através do proxy reverso do Nginx

---

## 📚 Recursos Adicionais

- [Documentação Angular HttpClient](https://angular.io/guide/http)
- [Documentação Spring Security CORS](https://docs.spring.io/spring-security/reference/servlet/integrations/cors.html)
- [Configuração Nginx como Proxy Reverso](https://docs.nginx.com/nginx/admin-guide/web-server/reverse-proxy/)
- [Docker Compose Networking](https://docs.docker.com/compose/networking/)