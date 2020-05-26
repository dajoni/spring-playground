plugins {
	id ("org.springframework.boot")
    id ("io.freefair.lombok") version "5.0.1"
}

dependencies {
	compileOnly("org.projectlombok:lombok:1.18.12")
	compileOnly("org.springframework.boot:spring-boot-configuration-processor")
    implementation(project(":avro-schema"))
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("io.confluent:kafka-avro-serializer:5.5.0")
    implementation("org.apache.kafka:kafka_2.12:5.5.0-ccs")
//    implementation('log4j:log4j:1.2.17')

    implementation("org.springframework.kafka:spring-kafka") {
        // using clients from Confluent
		exclude (module = "kafka-clients")
    }
	testCompileOnly("org.projectlombok:lombok:1.18.12")
	testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
        // using junit 5
		exclude (group = "junit", module = "junit")
		exclude (group = "org.junit.vintage", module = "junit-vintage-engine")
	}
    testImplementation("org.springframework.kafka:spring-kafka-test") {
        // using clients from Confluent
		exclude (module = "kafka-clients")
	}
    testImplementation("org.apache.kafka:kafka-clients:5.5.0-ccs:test")
//    testImplementation('log4j:log4j:1.2.17')

    configurations.all {
        // using slf4j from spring
        exclude (group = "log4j", module = "log4j")
        exclude (group = "org.slf4j", module = "slf4j-log4j12")
    }
}
