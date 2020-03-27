#!/bin/bash

set -eu

# @file devcontrol/global/startup.sh
# @brief devcontrol startup script and functions
echo "Hotel Covid (c) 2020"
echo
cp -n backend/.env.dist backend/.env || true

