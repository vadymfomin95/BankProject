FROM harbor.avalaunch.aval/docker-hub-proxy/library/openjdk:17-jdk

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]