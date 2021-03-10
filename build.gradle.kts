plugins {
    kotlin("jvm") version "1.4.31"

    id("maven-publish")
    id("java-library")
}

group = "com.merkenlabs.googleapiwrapper.docs"
version = "0.1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("com.google.apis:google-api-services-docs:v1-rev20210302-1.31.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.getByName<Jar>("jar"){
    enabled = true
}

java {
    withSourcesJar()
}

publishing{
    publications{
        create<MavenPublication>("google-docs-api-wrapper"){
            from(components["java"])
        }
    }
}