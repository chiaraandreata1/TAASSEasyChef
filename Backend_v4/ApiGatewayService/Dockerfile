FROM openjdk
#RUN addgroup -S spring && adduser -S spring -G spring
VOLUME /tmp
EXPOSE 8080
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar chef-easychef.jar
ENTRYPOINT ["java", "-jar", "/chef-easychef.jar"]
