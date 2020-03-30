#!/bin/bash

# Get host UID/GID
USER_ID=$(ls -n /workspace/README.md |awk '{print $3}')
GROUP_ID=$(ls -n /workspace/README.md |awk '{print $4}')

# Chnge UID/GID of ubuntu user if the UID is not 0 (root user ID, mac/windows volume)
if [ ${USER_ID} -ne 0 ]; then
    usermod -u ${USER_ID} ubuntu
    groupmod -g ${GROUP_ID} ubuntu
    chown -R ubuntu.ubuntu /home/ubuntu
fi

# Execute mvn install with non privileged user
su ubuntu bash -c "mvn clean install"

# Start the with:
# - Remote debug in 5005 port https://intellij-support.jetbrains.com/hc/en-us/community/posts/206844695-handshake-failed-error-with-Remote-Debugging
# - Non privileged user
su ubuntu bash -c "java -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005 -jar ./target/hotelcovid19-1.0-SNAPSHOT.jar"
