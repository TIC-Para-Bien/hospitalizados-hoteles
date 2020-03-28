FROM maven:3.6.3-jdk-11-slim

#ENV JAVA_OPTS=""
#ENV NODE=""
#ENV DOMAIN=""

WORKDIR /workspace
COPY start.sh /
COPY wait-for-it.sh /

CMD /start.sh
