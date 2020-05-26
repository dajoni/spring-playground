plugins {
	id ("org.springframework.boot")
	id ("io.freefair.lombok") version "5.0.1"
}

dependencies {
	runtimeOnly("org.projectlombok:lombok:1.18.12")
	runtimeOnly("org.springframework.boot:spring-boot-configuration-processor")

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation(project(":avro-schema"))
	implementation("org.springframework.boot:spring-boot-starter-hateoas") {
		exclude("org.springframework.boot", "spring-boot-starter-tomcat")
	}
	implementation("org.springframework.boot:spring-boot-starter-undertow")


	testCompileOnly("org.projectlombok:lombok:1.18.12")
	testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude (group = "junit", module = "junit")
		exclude (group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}
