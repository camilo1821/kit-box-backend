# Etapa 1: Compilar el proyecto con Gradle
FROM --platform=linux/arm64 eclipse-temurin:21-jdk-jammy AS build

WORKDIR /workspace

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew

COPY src src

RUN ./gradlew clean bootJar -x test --no-daemon \
    && JAR_FILE="$(find build/libs -maxdepth 1 -type f -name '*.jar' ! -name '*-plain.jar' | head -n 1)" \
    && test -n "$JAR_FILE" \
    && cp "$JAR_FILE" /tmp/app.jar

# Etapa 2: Ejecutar el JAR en ARM64 nativo
FROM --platform=linux/arm64 eclipse-temurin:21-jre-jammy

WORKDIR /app

RUN groupadd -r app && useradd -r -g app app

COPY --from=build --chown=app:app /tmp/app.jar /app/app.jar

USER app

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx350m", "-jar", "/app/app.jar"]
