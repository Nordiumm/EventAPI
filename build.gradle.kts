plugins {
    id("java")
}

group = "net.nordiumm"
version = "1.0.0"

repositories {
    mavenCentral()

    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.2.build.+")
    implementation("com.google.code.gson:gson:2.13.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}