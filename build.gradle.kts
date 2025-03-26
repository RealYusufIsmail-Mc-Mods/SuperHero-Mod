plugins {
    `java-library`
    `maven-publish`
    id("net.neoforged.moddev") version "2.0.79"
    idea
    id("com.diffplug.spotless") version "6.25.0"
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
}

version = project.property("mod_version").toString()
group = project.property("mod_group_id").toString()

repositories {
    mavenLocal()
}

base {
    archivesName.set(project.property("mod_id").toString())
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// NeoForge configuration
neoForge {
    version = project.property("neo_version").toString()

    parchment {
        mappingsVersion.set(project.property("parchment_mappings_version").toString())
        minecraftVersion.set(project.property("parchment_minecraft_version").toString())
    }

    runs {
        register("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("gameTestServer") {
            type.set("gameTestServer")
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("data") {
            clientData()
            programArguments.addAll(
                "--mod", project.property("mod_id").toString(),
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel.set(org.slf4j.event.Level.DEBUG)
        }
    }

    mods {
        register(project.property("mod_id").toString()) {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets.main.get().resources.srcDir("src/generated/resources")

configurations {
    val localRuntime by creating
    runtimeClasspath {
        extendsFrom(localRuntime)
    }
}

dependencies {
    compileOnly("org.projectlombok:lombok:${project.property("lombokVersion")}")
    annotationProcessor("org.projectlombok:lombok:${project.property("lombokVersion")}")
}

val generateModMetadata by tasks.registering(ProcessResources::class) {
    val replaceProperties = mapOf(
        "minecraft_version" to project.property("minecraft_version"),
        "minecraft_version_range" to project.property("minecraft_version_range"),
        "neo_version" to project.property("neo_version"),
        "neo_version_range" to project.property("neo_version_range"),
        "loader_version_range" to project.property("loader_version_range"),
        "mod_id" to project.property("mod_id"),
        "mod_name" to project.property("mod_name"),
        "mod_license" to project.property("mod_license"),
        "mod_version" to project.property("mod_version"),
        "mod_authors" to project.property("mod_authors"),
        "mod_description" to project.property("mod_description")
    )

    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}

sourceSets.main.get().resources.srcDir(generateModMetadata)
neoForge.ideSyncTask(generateModMetadata)

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

spotless {
    java {
        targetExclude("build/**")
        palantirJavaFormat()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        removeUnusedImports()
        licenseHeader(
            """/*
 * Copyright 2025 RealYusufIsmail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ """
        )
    }
}