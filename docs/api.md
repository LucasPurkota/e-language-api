`api.md`
```markdown
# Endpoints

## Autenticação

### POST `/api/v1/auth/login`
- **Body:** `{ "email": "...", "senha": "..." }`
- **Response:** `{ "token": "..." }`

## Usuários

### GET `/api/v1/usuarios/{id}`
- **Headers:** `Authorization: Bearer <token>`
- **Response:** Dados do usuário

### POST `/api/v1/usuarios`
- **Headers:** `Authorization: Bearer <token>`
- **Body:** Dados do usuário