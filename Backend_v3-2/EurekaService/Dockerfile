FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#VOLUME /tmp
EXPOSE 8761
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar discoveryservice.jar
ENTRYPOINT ["java", "-jar", "/discoveryservice.jar"]
