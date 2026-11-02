plugins {
    kotlin("jvm") version "2.4.21"
    kotlin("plugin.serialization") version "2.4.21"
    application
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:3.6.1")
    implementation("io.ktor:ktor-server-netty:3.6.1")
    implementation("io.ktor:ktor-server-content-negotiation:3.6.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.6.1")
    implementation("io.ktor:ktor-server-status-pages:3.6.1")
    implementation("io.ktor:ktor-server-call-logging:3.6.1")
    implementation("io.ktor:ktor-server-openapi:3.6.1")
    implementation("io.ktor:ktor-server-swagger:3.6.1")
    implementation("ch.qos.logback:logback-classic:1.6.6")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-test-host:3.6.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.14.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("com.example.tasks.ApplicationKt")
}

tasks.test {
    useJUnitPlatform()
}
