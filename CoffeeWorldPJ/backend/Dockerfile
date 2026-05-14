# Etapa de build com Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia apenas o pom.xml primeiro (para cache de dependências)
COPY pom.xml .

# Baixa as dependências do Maven
RUN mvn dependency:go-offline

# Copia todo o código-fonte do projeto Spring Boot
COPY . .

# Executa o build (sem testes)
RUN mvn clean package -DskipTests

# Etapa final com JRE mais leve para rodar o app (Java 21)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o JAR gerado no build anterior
COPY --from=build /app/target/*.jar app.jar

# Porta exposta
EXPOSE 8080

# Perfil 'prod' ativado via variavel de ambiente SPRING_PROFILES_ACTIVE=prod no Railway
ENTRYPOINT ["java", "-jar", "app.jar"]
