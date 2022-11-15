FROM openjdk:11-alpine
RUN addgroup -S spring && adduser -S spring -G spring
VOLUME /tmp
EXPOSE 8082
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar recipe-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","recipe-service.jar"]

