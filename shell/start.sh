#!/bin/bash

#  params
SERVER_PORT=8080
SERVER_ACTIVE="online"
SERVER_NAME="springboot-host-0.0.1-SNAPSHOT.jar"

HOST=$(/sbin/ifconfig -a |grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:"|head -1)
#shell
nohup java -jar   \
  -Xmx256M -Xms256M \
  -Dfile.encoding=UTF-8 \
  -Dserver.port=${SERVER_PORT} \
  -Dspring.profiles.active=${SERVER_ACTIVE} \
  -Dlogging.config=./config/logback-spring.xml \
  -Dspring.config.location=./config/application.yml \
   ${SERVER_NAME}    >console.log &

echo -e "\033[34m  App running at:    \033[0m"
echo -e "\033[34m     - Running  server params  spring.profiles.active:  ${SERVER_ACTIVE}  server.port: ${SERVER_PORT} \033[0m"
echo -e ""
echo -e "\033[34m     - Local:      http://localhost:${SERVER_PORT}/   \033[0m"
echo -e "\033[34m     - Network:    http://${HOST}:${SERVER_PORT}/ \033[0m"
echo -e "\033[34m     - SwaggerApi: http://${HOST}:${SERVER_PORT}/doc.html#/home \033[0m"
