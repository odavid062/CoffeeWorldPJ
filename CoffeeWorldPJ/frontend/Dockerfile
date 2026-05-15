# Etapa de build
FROM node:18-alpine AS build
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# Etapa de producao com nginx
FROM nginx:alpine

# Copia o app Angular buildado
COPY --from=build /app/dist/spa-coffee-world/browser /usr/share/nginx/html

# Copia o template do nginx (com placeholder ${BACKEND_URL})
COPY nginx.conf.template /etc/nginx/templates/default.conf.template

# O nginx:alpine ja tem suporte a envsubst via /etc/nginx/templates/
# Ao iniciar, substitui ${BACKEND_URL} pelo valor da variavel de ambiente
# e gera /etc/nginx/conf.d/default.conf automaticamente

EXPOSE 80
