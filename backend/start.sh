#!/bin/bash
mvn clean install

java $@ -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar ./target/hotelcovid19-1.0-SNAPSHOT.jar
