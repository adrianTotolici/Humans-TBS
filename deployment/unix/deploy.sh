#!/usr/bin/env bash

USER_HOME=$(eval echo ~${SUDO_USER})

if [ -d "${USER_HOME}/TheHumans" ]; then
  rm -rf ${USER_HOME}/TheHumans
fi

mkdir -p ${USER_HOME}/TheHumans/bin
mkdir -p ${USER_HOME}/TheHumans/lib

cp -avr ${USER_HOME}/projects/The\ Humans/assets/ ${USER_HOME}/TheHumans/assets/
cp -avr ${USER_HOME}/projects/The\ Humans/out/artifacts/TheHumansJar/TheHumansJar.jar ${USER_HOME}/TheHumans/bin/
cp -avr ${USER_HOME}/projects/The\ Humans/lib/native-linux/* ${USER_HOME}/TheHumans/lib/

chmod -R 777 ${USER_HOME}/TheHumans

java -jar ${USER_HOME}/projects/The\ Humans/deployment/jarsplice-0.40.jar

