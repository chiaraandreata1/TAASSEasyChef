FROM openjdk
#RUN addgroup -S spring && adduser -S spring -G spring
VOLUME /tmp
EXPOSE 8080
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar gateway-service.jar
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]
