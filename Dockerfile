FROM openjdk:11-jre-slim
WORKDIR /opt/pisio_back
COPY target/*.jar simpleApp.jar
ARG DEFAULT_PORT=8080
ENV PORT $DEFAULT_PORT
EXPOSE $PORT
ENTRYPOINT [ "java", "-jar", "simpleApp.jar" ]