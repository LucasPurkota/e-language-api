# FROM eclipse-temurin:17-jdk
# WORKDIR /app
# COPY target/e-language-api-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "app.jar"]

# Use uma imagem base mais leve e específica
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Instala Maven e faz o build
RUN apk add --no-cache maven
RUN mvn clean package -DskipTests

# Imagem final
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o JAR buildado
COPY --from=build /app/target/e-language-api-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta
EXPOSE 8080

# Health check para Render
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
