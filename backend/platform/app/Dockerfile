FROM maven:3.6.3-jdk-11-slim

# Put your code folder here as a docker volume
WORKDIR /workspace

# Assets
COPY start.sh /
COPY wait-for-it.sh /

# Start script
CMD /start.sh
