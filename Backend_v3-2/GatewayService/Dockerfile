FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#VOLUME /tmp
EXPOSE 8080
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar gatewayservice.jar
ENTRYPOINT ["java", "-jar", "/gatewayservice.jar"]
