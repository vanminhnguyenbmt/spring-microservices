FROM openjdk:8-jre-slim-buster

# Set image information, but could be set to different location from command line
ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="Stock Service"
ARG MAINTAINER="Louis Van <vanminhnguyenbmt@gmail.com>"

LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

COPY target/stock-service-0.0.1-SNAPSHOT.jar /usr/local/lib/stock-service.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/stock-service.jar"]