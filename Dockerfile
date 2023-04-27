FROM openjdk:17 AS builder
VOLUME /tmp
CMD mkdirs /app/files
ADD ./build/libs/online-courses-0.0.1-SNAPSHOT.jar app/online-courses-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-XX:+UseSerialGC", "-Xss512k", "-Xmx64M","-jar","online-courses-0.0.1-SNAPSHOT.jar"]