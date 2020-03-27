#!/bin/bash

set -eu

# @description Prepare docker platform
#
# @example
#   hello-world
#
# @arg $1 Task: "brief", "help" or "exec"
#
# @exitcode 0
#
# @stdout "Not implemented" message if the requested task is not implemented
#
function prepare() {

    # Init
    local briefMessage="Prepare the docker platform"
    local helpMessage="Build local docker images and pull dockerhub images"

    # Task choosing
    case $1 in
        brief)
            showBriefMessage "${FUNCNAME[0]}" "$briefMessage"
            ;;
        help)
            showHelpMessage "${FUNCNAME[0]}" "$helpMessage"
            ;;
        exec)
            cd backend
            docker-compose -f src/main/docker/docker-compose.yml pull
            docker-compose -f src/main/docker/docker-compose.yml build
            ;;
        *)
            showNotImplemtedMessage "$1" "${FUNCNAME[0]}"
            return 1
    esac
}

# Main
prepare "$@"
