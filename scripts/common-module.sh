#!/usr/bin/env bash
mvn clean install -f pom-base/pom-build.xml
mvn clean install -f pom-base/pom-cloud.xml
mvn clean install -f pom-base/pom-service.xml
mvn clean install -f pom-base/pom-service-tracking.xml
cd ./common
mvn -U clean install -DskipTests=true