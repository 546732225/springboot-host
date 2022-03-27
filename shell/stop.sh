#!/bin/bash
# shellcheck disable=SC2009
# params
SERVER_NAME="springboot-host-0.0.1-SNAPSHOT.jar"

#shell
PID=$( ps -ef | grep ${SERVER_NAME} | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo -e "\033[34m      Application is already stopped \033[0m"
else
    echo -e "\033[36m      Kill thread...  ${PID}        \033[0m"
    kill "$PID"
fi