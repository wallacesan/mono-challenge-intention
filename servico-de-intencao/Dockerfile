# Etapa 1: Construir a aplicação
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Definir diretório de trabalho
WORKDIR /app

# Copiar o arquivo de configuração do Maven
COPY pom.xml .

# Baixar as dependências do Maven (utilizando cache do Docker)
RUN mvn dependency:go-offline

# Copiar todo o código do projeto para dentro do container
COPY src ./src

# Compilar o projeto e criar o arquivo JAR
RUN mvn package -DskipTests

# Etapa 2: Criar a imagem final
FROM eclipse-temurin:17-jdk-alpine

# Definir diretório de trabalho
WORKDIR /app

# Copiar o JAR da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expor a porta em que a aplicação será executada
EXPOSE 8080

# Comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]