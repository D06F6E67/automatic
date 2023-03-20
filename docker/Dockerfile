FROM openjdk:8-jre

ADD target/automatic-*.jar /work/automatic.jar
WORKDIR /work

ENV TZ = Asia/Shanghai
ENV JAVA_OPTS = ""

ENTRYPOINT java ${JAVA_OPTS} -jar automatic.jar