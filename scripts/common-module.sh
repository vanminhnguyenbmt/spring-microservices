#!/usr/bin/env bash
mvn clean install -f pom-base/pom-build.xml
mvn clean install -f pom-base/pom-cloud.xml
cd ./common
mvn -U clean install -DskipTests=true