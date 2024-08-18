# Etapa 1: Construir a aplicação
FROM maven:3.8.3-openjdk-17 as build

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

FROM openjdk:17

# Definir diretório de trabalho
WORKDIR /app

# Copiar o JAR da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expor a porta em que a aplicação será executada
EXPOSE 8081

# Comando de inicialização da aplicação
CMD ["java", "-jar", "app.jar", "--server.address=0.0.0.0"]