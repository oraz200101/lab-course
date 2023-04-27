FROM amazoncorretto:17.0.0-alpine
VOLUME /tmp
CMD mkdirs /app/files
COPY ./build/libs/online-courses-0.0.1-SNAPSHOT.jar /online-courses-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "online-courses-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080