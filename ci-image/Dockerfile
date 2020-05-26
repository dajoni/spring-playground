FROM ubuntu:bionic AS updated-with-java


#Prepare for docker repo
RUN set -ex \
      && apt-get update \
      && apt-get upgrade --yes \
      && apt-get install --yes curl gnupg openjdk-11-jdk git sshpass unzip ca-certificates wget \
      && curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add - \
      && update-ca-certificates \
      && echo "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable" >> /etc/apt/sources.list \
      && apt-get update 

FROM updated-with-java 

ENV GRADLE_VERSION=6.4
ENV GRADLE_HOME=/opt/gradle
ENV GRADLE_FOLDER=/root/.gradle


# Download and extract gradle & docker
RUN set -ex \
      && apt-get install --yes docker-ce \
      && curl -O --location --silent --show-error https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
      && unzip -q gradle-${GRADLE_VERSION}-bin.zip -d /usr/lib \
      && ln -s /usr/lib/gradle-${GRADLE_VERSION}/bin/gradle /usr/bin/ \
      && rm -rf gradle-${GRADLE_VERSION}-bin.zip \
          /usr/lib/gradle-${GRADLE_VERSION}/bin/gradle.bat \
          /usr/lib/gradle-${GRADLE_VERSION}/getting-started.html \
          /usr/lib/gradle-${GRADLE_VERSION}/media \
          /opt/jdk/*src.zip \
          /var/lib/apt/lists/* 