# Spring Playground 
A testing project for me having fun with Spring, Kafka and Graphs :)


## Technologies tested
* Spring Boot 2
* Docker 
* Avro for schema 
* GitLab CI - https://gitlab.com/dajoni/spring-playground

# CI pipeline
First step of this pipeline builds the combined Gradle + Docker-in-docker image using gitlab-ci. The use of caching + local docker repository speeds things up.

The second step uses Gradle to build, execute tests and push a docker image.

The Dockerfile for the CI image is found in the folder `ci-image`.

# Data
I have bundled the dataset from https://snap.stanford.edu/data/wiki-Talk.html into the `event-producer`. 
The `avro-schema` contains a simple schema for events with a bit of added random meta data.


# What I want to integrate later
Plans for this project can be found here https://trello.com/b/pAussyZZ/spring-playground



 