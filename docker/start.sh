#!/bin/sh
jar=automatic-*.jar

cd /work
if [ -f $jar ]; then
  if [ -f ${JAR_NAME}.jar ]; then
    date=$(date +%y%m%d)
    yes| mv ${JAR_NAME}.jar backup/${JAR_NAME}-$date.jar 
  fi
  mv $jar ${JAR_NAME}.jar
fi 

if [ -f ${JAR_NAME}.jar ]; then
  java -jar ${JAR_NAME}.jar
  #java -jar ${JAR_NAME}.jar --spring.profiles.active=${ACTIVE}
  #java -Dloader.path=lib/ -jar ${JAR_NAME}.jar --spring.profiles.active=${ACTIVE}
else
  echo "${JAR_NAME}.jar not exist"
fi
