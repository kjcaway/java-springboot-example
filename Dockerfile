# CMD ["./gradlew", "clean", "build", "-x", "test", "--console", "plain"]
FROM amazoncorretto:11

ARG JAR_FILE_PATH=build/libs/java-demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]