FROM eclipse-temurin:20-jdk as build
WORKDIR /workspace/app
COPY src src
COPY gradle gradle
COPY build.gradle.kts .
COPY gradlew .
COPY settings.gradle.kts .
RUN --mount=type=cache,target=/root/.gradle ./gradlew bootJar -i -x test

FROM eclipse-temurin:20-jre
VOLUME /app
COPY --from=build /workspace/app/build/libs/*.jar application.jar
ENTRYPOINT ["java", "--enable-preview", "-jar", "application.jar"]
