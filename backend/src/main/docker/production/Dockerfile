FROM openjdk:13

ADD ./*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "./app.jar"]

EXPOSE 8080 8021
