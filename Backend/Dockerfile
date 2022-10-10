FROM openjdk:11-alpine
RUN addgroup -S spring && adduser -S spring -G spring
VOLUME /tmp
EXPOSE 8081 8082
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar recipe-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","recipe-service.jar"]







# file per contenere tutti i comandi che servono a cerare un'immagine docker con dentro un'app