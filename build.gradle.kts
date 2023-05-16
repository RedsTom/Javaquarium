plugins {
    id("java")
}

group = "fr.rauster"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.0")
    implementation("org.json:json:20230227")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}