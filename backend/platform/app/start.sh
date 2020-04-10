#!/bin/bash

# Start the with remote debug in 5005 port https://intellij-support.jetbrains.com/hc/en-us/community/posts/206844695-handshake-failed-error-with-Remote-Debugging
packageFile="target/hotelcovid*.jar"
java -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005 -jar "${packageFile}"
