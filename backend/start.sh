#!/bin/bash
mvn clean install

java $@ -jar ./target/hotelcovid19-1.0-SNAPSHOT.jar
