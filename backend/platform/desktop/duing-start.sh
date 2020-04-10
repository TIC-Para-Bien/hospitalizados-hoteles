#!/bin/bash

# Get host UID/GID
USER_ID=$(ls -n /workspace/README.md |awk '{print $3}')
GROUP_ID=$(ls -n /workspace/README.md |awk '{print $4}')

# Chnge UID/GID of ubuntu user if the UID is not 0 (root user ID, mac/windows volume)
if [ "${USER_ID}" -ne 0 ]; then
    usermod -u "${USER_ID}" ubuntu
    groupmod -g "${GROUP_ID}" ubuntu
    chown -R ubuntu.ubuntu /home/ubuntu
fi

/bin/bash /root/startup.sh
