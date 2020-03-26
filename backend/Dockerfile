ARG maven_version=3.5.3

FROM maven:${maven_version}-jdk-8-alpine as compilation

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn package -Dmaven.test.skip=true

#FROM openjdk:8-jre-alpine
FROM openjdk:13

EXPOSE 8080

COPY --from=compilation /build/target/hotelcovid19-1.0-SNAPSHOT.jar .

CMD java -jar hotelcovid19-1.0-SNAPSHOT.jar
