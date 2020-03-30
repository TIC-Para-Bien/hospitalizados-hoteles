FROM maven:3.6.3-jdk-11-slim

# Put your code folder here as a docker volume
WORKDIR /workspace

# Assets
COPY start.sh /
COPY wait-for-it.sh /

# Add user for unprivileged app execution
RUN addgroup ubuntu && \
    useradd -g ubuntu -s /bin/bash -m ubuntu && \
    echo "PATH=$PATH" >> /home/ubuntu/.profile

# Start script
CMD /start.sh
