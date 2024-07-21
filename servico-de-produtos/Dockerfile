# Use a imagem oficial do Node.js 10 como base
FROM node:20.15.1-slim

# Define o diretório de trabalho dentro do container
WORKDIR /usr/src/app

# Copia o package.json e package-lock.json (se disponível)
COPY package*.json ./

# Instala as dependências
RUN npm install

# Instala as dependências de desenvolvimento
RUN npm install -D @types/express @types/body-parser ts-node typescript

# Copia o restante do código da aplicação
COPY . .

# # Compila o código TypeScript
RUN npx tsc

# Expõe a porta que sua aplicação irá rodar
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["npm", "start"]