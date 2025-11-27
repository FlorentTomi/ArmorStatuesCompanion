import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.modrinth.minotaur)
    alias(libs.plugins.curseGradle)
}

version = libs.versions.armorStatuesCompanion.get()

repositories {
    mavenCentral()

    // Mojang / Fabric for MC + mappings (Loom also wires some of this, but it's safe)
    maven {
        name = "Fabric"
        url = uri("https://maven.fabricmc.net/")
    }

    // WispForest for owo, etc.
    maven {
        url = uri("https://maven.wispforest.io")
    }
}

dependencies {
    "minecraft"(libs.minecraft)
    "mappings"(libs.yarn.mappings) {
        artifact {
            classifier = "v2"
        }
    }

    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
    modImplementation(libs.fabric.kotlin)

    modImplementation(libs.owo.lib)
    include(libs.owo.sentinel)
}

tasks.withType<ProcessResources>() {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 21
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "21"
    }
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val modrinthToken: String? = System.getenv("MODRINTH_TOKEN")
if (modrinthToken != null) {
    modrinth {
        token.set(System.getenv("MODRINTH_TOKEN"))
        projectId.set("UE4M9RbW")
        versionNumber.set(libs.versions.armorStatuesCompanion)
        versionType.set("release")
        uploadFile.set(tasks.remapJar)
        gameVersions.addAll(libs.versions.minecraft.get())
        loaders.add("fabric")
        changelog.set(rootProject.file("CHANGELOG.md").readText())
        syncBodyFrom.set(rootProject.file("DESCRIPTION.md").readText())
        debugMode.set(true)
        dependencies {
            required.project("fabric-api")
            required.project("fabric-language-kotlin")
            required.project("owo-lib")
        }
    }
}

val curseForgeToken: String? = System.getenv("CURSEFORGE_TOKEN")
if (curseForgeToken != null) {
    curseforge {
        apiKey = System.getenv("CURSEFORGE_TOKEN")
        project {
            id = "904018"
            changelog = rootProject.file("CHANGELOG.md")
            releaseType = "release"
            addGameVersion(libs.versions.minecraft.get())
            mainArtifact(tasks.remapJar.get())
            relations {
                requiredDependency("fabric-api")
                requiredDependency("fabric-language-kotlin")
                requiredDependency("owo-lib")
            }

            options {
                debug = true
            }
        }
    }
}
