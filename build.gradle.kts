plugins {
    id("org.springframework.boot") version "2.2.7.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
//	id ("com.palantir.docker") version "0.25.0"
//	id ("com.palantir.git-version") version "0.12.3"
//	id ("java")
//	id ("project-report") apply false
}


//docker.dependsOn(tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar"))
//docker {
//	name("registry.gitlab.com/dajoni/spring-playground/resource-service:${project.version}")
//	files bootJar.outputs
//	dockerfile file("src/main/docker/Dockerfile")
//}

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