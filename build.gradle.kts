plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("kapt") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
}

group = "com.leijendary.spring"
version = "1.0.0"
description = "Spring Boot WebSocket Template for the Microservice Architecture or general purpose"
java.sourceCompatibility = JavaVersion.VERSION_19

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    testImplementation {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

repositories {
    mavenCentral()
}

kapt {
    arguments {
        arg("mapstruct.unmappedTargetPolicy", "IGNORE")
    }
    javacOptions {
        option("--enable-preview")
    }
}

dependencies {
    // Kotlin
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))

    // Spring Boot Starter
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    // Spring Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Spring Security
    implementation("org.springframework.security:spring-security-web")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    testImplementation("org.springframework.security:spring-security-test")

    // Spring Retry
    implementation("org.springframework.retry:spring-retry")

    // Spring Kafka
    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test")

    // MapStruct
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
    testImplementation("org.mapstruct:mapstruct-processor:1.5.3.Final")

    // OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")

    // Devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // Tracing
    implementation("com.github.loki4j:loki-logback-appender:1.4.0")
    implementation("io.micrometer:micrometer-observation")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.opentelemetry:opentelemetry-exporter-zipkin")

    // Test
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")

    // Test Containers
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:kafka")
    testImplementation("org.testcontainers:rabbitmq")
}

dependencyManagement {
    imports {
        mavenBom("io.micrometer:micrometer-tracing-bom:1.0.1")
        mavenBom("org.testcontainers:testcontainers-bom:1.18.3")
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all", "-Xjvm-enable-preview")
            jvmTarget = "19"
        }
    }

    compileJava {
        options.compilerArgs.add("--enable-preview")
    }

    bootJar {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    jar {
        enabled = false
    }

    test {
        jvmArgs = listOf("--enable-preview")
        useJUnitPlatform()
    }

    processResources {
        filesMatching("application.yaml") {
            expand(project.properties)
        }
    }
}
