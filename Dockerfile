FROM openjdk:17
VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE=target/quarkus-api-pn-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} quarkus-api-pn.jar
ENTRYPOINT ["java","-jar","/quarkus-api-pn.jar"]
