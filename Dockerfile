FROM openjdk:17
VOLUME /tmp
COPY build/libs/online-courses-0.0.1-SNAPSHOT.jar /online-courses-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","online-courses-0.0.1-SNAPSHOT.jar"]