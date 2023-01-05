FROM openjdk
#RUN addgroup -S spring && adduser -S spring -G spring
VOLUME /tmp
EXPOSE 8761
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]
