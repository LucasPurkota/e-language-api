# Guia de Integração: Frontend Angular com Backend Spring Boot

## Visão Geral

Este documento explica as mudanças feitas para conectar o frontend Angular com o backend Spring Boot no projeto E-Language API, usando Docker e Nginx como proxy reverso.

## Arquitetura da Solução

![Arquitetura](https://i.imgur.com/MkHyKtX.png)

A arquitetura implementada consiste em:

1. **Frontend Angular** com Nginx (porta 3000)
2. **Backend Spring Boot** (porta 8080)
3. **Banco de Dados PostgreSQL** (porta 5432)
4. **Rede Docker** para comunicação entre serviços

## Principais Mudanças Realizadas

### 1. Configuração de Ambiente no Angular

O arquivo `environment.ts` foi modificado para usar um caminho relativo em vez de absoluto:

```typescript
// De:
export const environment = {
  production: true,
  apiUrl: 'http://backend:8080/api/v1'
};

// Para:
export const environment = {
  production: true,
  apiUrl: '/api/v1'
};
```

Isto permite que as requisições sejam enviadas ao mesmo host (onde o Nginx está rodando), evitando problemas de CORS e DNS.

### 2. Configuração do Proxy Reverso no Nginx

O Nginx foi configurado como proxy reverso para encaminhar requisições do frontend para o backend:

```nginx
# Proxy reverso para o backend
location /api/v1/ {
    proxy_pass http://backend:8080/api/v1/;
    proxy_http_version 1.1;
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
    proxy_connect_timeout 60s;
    proxy_send_timeout 60s;
    proxy_read_timeout 60s;
    
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
```

### 3. Serviço de Comunicação no Angular

O serviço `usuario.service.ts` foi criado para encapsular a comunicação com a API:

```typescript
@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = `${environment.apiUrl}/usuarios`;
  
  constructor(private http: HttpClient) {}

  getAll(): Observable<UsuarioListResponse[]> {
    return this.http.get<UsuarioListResponse[]>(this.apiUrl);
  }

  // ... outros métodos CRUD
}
```

## Como Funciona a Comunicação

1. **Solicitação do Cliente**: O navegador acessa `http://localhost:3000`
2. **Carregamento da Aplicação Angular**: O Nginx serve os arquivos estáticos do Angular
3. **Chamadas de API**:
   - Angular faz uma requisição para `/api/v1/usuarios`
   - O Nginx intercepta essa requisição
   - O Nginx encaminha para `http://backend:8080/api/v1/usuarios`
   - O backend Spring Boot processa a solicitação
   - O Nginx retorna a resposta para o Angular

## Benefícios Desta Arquitetura

1. **Evita Problemas de CORS**: Todas as solicitações parecem vir do mesmo host
2. **Resolve Problemas de DNS**: O frontend não precisa resolver o nome "backend"
3. **Segurança Melhorada**: O backend não fica diretamente exposto ao cliente
4. **Escalabilidade**: Permite escalar frontend e backend independentemente

## Testando a Integração

Use o comando:
```bash
./commands.sh test-front
```

Este comando verifica:
- Se o frontend está acessível
- Se o backend está acessível diretamente
- Se o proxy reverso está funcionando corretamente

Para depurar problemas de conexão entre frontend e backend:
```bash
./commands.sh debug-proxy
```

## URLs Importantes

- **Frontend**: http://localhost:3000
- **Backend API (direto)**: http://localhost:8080/api/v1
- **Backend API (via proxy)**: http://localhost:3000/api/v1
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html