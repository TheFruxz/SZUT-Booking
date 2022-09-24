val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
	application
	kotlin("jvm") version "1.7.10"
	id("io.ktor.plugin") version "2.1.1"
	kotlin("plugin.serialization") version "1.7.10"
}

group = "de.szut"
version = "0.0.1"
application {
	mainClass.set("de.szut.ApplicationKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	mavenCentral()
	maven("https://jitpack.io")
}

dependencies {
	implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
	implementation("io.ktor:ktor-server-sessions-jvm:$ktor_version")
	implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
	implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
	implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
	implementation("ch.qos.logback:logback-classic:$logback_version")
	implementation("com.github.TheFruxz.MoltenKT:moltenkt-core:1.0-PRE-17")
	testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
	testImplementation(kotlin("test-junit", kotlin_version))
}