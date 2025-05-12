# ---------- Stage 1: build ----------
FROM maven:3.9.7-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copie só o que raramente muda p/ aproveitar cache
COPY pom.xml ./
COPY mvnw* ./
COPY .mvn ./.mvn
RUN mvn -B dependency:go-offline

# Agora o código‑fonte
COPY src ./src

# Compila e empacota (os testes ainda rodam; troque por -DskipTests se preferir)
RUN mvn -B package

# ---------- Stage 2: runtime ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/target/ecocontrol-0.0.1-SNAPSHOT.jar app.jar

# Seu Spring Boot ouve em 8080 por padrão
EXPOSE 8080
ENV TZ=America/Sao_Paulo

ENTRYPOINT ["java","-jar","/app/app.jar"]
