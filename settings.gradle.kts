pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
        maven {
            name = "JCenter Gradle Plugins"
            url = uri("https://dl.bintray.com/gradle/gradle-plugins")
        }
    }
}
rootProject.name = "spring-playground"
include ("avro-schema", "event-producer", "event-consumer")