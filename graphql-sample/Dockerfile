FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim
COPY build/libs/graphql-sample-*-all.jar graphql-sample.jar
EXPOSE 8082
CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar graphql-sample.jar