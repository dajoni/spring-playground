plugins {
    id("org.springframework.boot") version "2.3.0.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
}

allprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin ("java")
    }

    tasks.withType<Test>() {
        useJUnitPlatform()
        testLogging.showStandardStreams = true
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    tasks.withType<JavaCompile>() {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    repositories {
        jcenter()
        maven { url = uri("https://packages.confluent.io/maven/") }
    }
}