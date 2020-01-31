import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    `maven-publish`
    id("org.jmailen.kotlinter") version "2.2.0"
    id("com.adarshr.test-logger") version "2.0.0"
    kotlin("jvm") version "1.3.70-eap-42"
}

group = "network.piction"
version = "1.0.2"

repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    mavenCentral()
}

dependencies {
    implementation("commons-codec:commons-codec:1.5")
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "github"
            url = uri("https://maven.pkg.github.com/piction-protocol/piction-utils")
            credentials {
                username = findProperty("RESOURCES_USERNAME") as String
                password = findProperty("RESOURCES_PASSWORD") as String
            }
        }
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
