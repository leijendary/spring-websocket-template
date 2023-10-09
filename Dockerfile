FROM azul/zulu-openjdk-alpine:20-jre-headless-latest
COPY build/libs/*.jar application.jar
ENTRYPOINT ["java", "--enable-preview", "-jar", "application.jar"]
