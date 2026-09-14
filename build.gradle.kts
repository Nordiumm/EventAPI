plugins {
    id("java")
    id("maven-publish")
}

group = "net.nordiumm"
version = "1.0.4"

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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "nixon-event-api"
        }
    }
}

tasks {
    processResources {
        val props = mapOf(
            "version" to version,
            "description" to project.description
        )

        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}