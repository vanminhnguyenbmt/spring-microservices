FROM openjdk:8-jre-slim-buster

# Set image information, but could be set to different location from command line
ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="Gateway Zuul"
ARG MAINTAINER="Louis Van <vanminhnguyenbmt@gmail.com>"

LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

COPY target/gateway-zuul-0.0.1-SNAPSHOT.jar /usr/local/lib/gateway-zuul.jar
EXPOSE 8762
ENTRYPOINT ["java","-jar","/usr/local/lib/gateway-zuul.jar"]