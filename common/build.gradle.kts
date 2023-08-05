plugins {
    `maven-publish`
}

architectury {
    val enabledPlatforms: String by rootProject
    common(enabledPlatforms.split(","))
}

loom {
    accessWidenerPath.set(file("src/main/resources/asc.accesswidener"))
}

dependencies {
    modImplementation(libs.fabric.loader)
    compileOnly(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = rootProject.property("archivesBaseName").toString()
            from(components.getByName("java"))
        }
    }

    repositories {
    }
}