import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    `kotlin-dsl`
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.2-SNAPSHOT" apply false
    kotlin("jvm") version "1.8.10" apply false
}

architectury {
    minecraft = rootProject.property("minecraftVersion").toString()
}

subprojects {
    apply(plugin = "dev.architectury.loom")

    dependencies {
		"minecraft"(rootProject.project.libs.minecraft)
        "mappings"("net.fabricmc:yarn:1.20.1+build.10")
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "architectury-plugin")
    apply(plugin = "maven-publish")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    base.archivesName.set("${rootProject.property("archivesBaseName")}")
    version = "${rootProject.property("modVersion")}+${rootProject.property("minecraftVersion")}"
    group = rootProject.property("mavenGroup").toString()

    repositories {
    }

    dependencies {
        "compileClasspath"(rootProject.project.libs.kotlin.gradlePlugin)
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    java {
        withSourcesJar()
    }

    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        jvmTarget = "17"
    }

    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = "17"
    }
}
