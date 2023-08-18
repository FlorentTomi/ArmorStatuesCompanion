import com.matthewprenger.cursegradle.CurseProject
import com.matthewprenger.cursegradle.CurseRelation
import com.matthewprenger.cursegradle.Options

plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("com.modrinth.minotaur") version "2.+"
    id("com.matthewprenger.cursegradle") version "1.4.0"
}

repositories {
    maven("https://maven.wispforest.io")
}

architectury {
    platformSetupLoomIde()
    fabric()
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
}

val common: Configuration by configurations.creating
val shadowCommon: Configuration by configurations.creating
val developmentFabric: Configuration = configurations.getByName("developmentFabric")
configurations {
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    developmentFabric.extendsFrom(configurations["common"])
}

dependencies {
    modImplementation(libs.fabric.loader)
    modApi(libs.fabric.api)
    modApi(libs.architectury.fabric)

    common(project(":common", configuration = "namedElements")) { isTransitive = false }
    shadowCommon(project(":common", configuration = "transformProductionFabric")) { isTransitive = false }
    modImplementation(libs.fabric.languageKotlin)

    modImplementation(libs.owo.lib)
    include(libs.owo.sentinel)
}

val javaComponent = components.getByName<AdhocComponentWithVariants>("java")
javaComponent.withVariantsFromConfiguration(configurations["sourcesElements"]) {
    skip()
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }

    shadowJar {
        exclude("architectury.common.json")
        configurations = listOf(project.configurations["shadowCommon"])
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        injectAccessWidener.set(true)
        inputFile.set(shadowJar.flatMap { it.archiveFile })
        dependsOn(shadowJar)
        archiveClassifier.set("fabric")
    }

    jar {
        archiveClassifier.set("dev")
    }

    sourcesJar {
        val commonSources = project(":common").tasks.getByName<Jar>("sourcesJar")
        dependsOn(commonSources)
        from(commonSources.archiveFile.map { zipTree(it) })
    }
}

if (rootProject.hasProperty("publish.modrinth_token")) {
    modrinth {
        token.set("${rootProject.property("publish.modrinth_token")}")
        projectId.set("armor-statues-companion")
        versionNumber.set("$version")
        versionType.set("release")
        loaders.set(listOf("fabric", "quilt"))
        uploadFile.set(tasks.remapJar)
        gameVersions.addAll("${rootProject.property("minecraftVersion")}")
        changelog.set(rootProject.file("CHANGELOG.md").readText())
        syncBodyFrom.set(rootProject.file("DESCRIPTION.md").readText())
        debugMode.set(true)
        dependencies {
            required.project("fabric-language-kotlin")
            required.project("fabric-api")
            required.project("owo-lib")
        }
    }
}

if (rootProject.hasProperty("publish.cf_token")) {
    curseforge {
        apiKey = "${rootProject.property("publish.cf_token")}"
        project(closureOf<CurseProject> {
            id = "904018"
            changelogType = "markdown"
            changelog = rootProject.file("CHANGELOG.md")
            releaseType = "release"
            addGameVersion("${rootProject.property("minecraftVersion")}")
            mainArtifact(tasks.remapJar.get().archiveFile)
            relations(closureOf<CurseRelation> {
                requiredDependency("fabric-language-kotlin")
                requiredDependency("fabric-api")
                requiredDependency("owo-lib")
            })
        })

        options(closureOf<Options> {
            debug = false
        })
    }
}