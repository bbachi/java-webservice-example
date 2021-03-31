# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine

# Copy war file
COPY todo.jar /todo.war

# run the app
CMD ["/usr/bin/java", "-jar", "/todo.war"]