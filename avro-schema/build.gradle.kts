plugins {
    id ("com.commercehub.gradle.plugin.avro") version "0.19.1"
}

group = "dajoni.spring.schema"

dependencies {
    implementation("org.apache.avro:avro:1.9.2")
}
