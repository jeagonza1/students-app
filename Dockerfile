FROM adoptopenjdk/openjdk8:alpine-jre

ARG JAR_FILE=target/*.jar
COPY ./target/graphql-api-student-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar" , "/app.jar"]

# Expose port 8080
EXPOSE 8080