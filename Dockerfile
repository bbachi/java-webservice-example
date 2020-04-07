FROM dockerimages-prd.jbhunt.com/jbh-alpine-jre11:latest
EXPOSE 8080
ADD target/todo.jar todo.jar
ENTRYPOINT exec java $JAVA_OPTS -XX:TieredStopAtLevel=1 -client -jar todo.jar
