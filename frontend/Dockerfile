# Estágio de build
FROM node:18-alpine AS build

WORKDIR /app

# Copiar package.json e package-lock.json (se existir)
COPY package*.json ./

# Instalar dependências
RUN npm ci

# Copiar código fonte
COPY . .

# Build da aplicação
RUN npm run build

# Estágio de produção
FROM nginx:alpine

# Remover conteúdo padrão do nginx
RUN rm -rf /usr/share/nginx/html/*

# Copiar arquivos de build para o nginx
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

# Copiar configuração customizada do nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expor porta 80
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]