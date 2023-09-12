FROM eclipse-temurin:20-jre
COPY build/libs/*.jar application.jar
ENTRYPOINT ["java", "--enable-preview", "-jar", "application.jar"]
