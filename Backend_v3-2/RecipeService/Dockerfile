FROM openjdk
#RUN addgroup -S spring && adduser -S spring -G spring
VOLUME /tmp
EXPOSE 8081
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar recipe-easychef.jar
ENTRYPOINT ["java", "-jar", "/recipe-easychef.jar"]
