FROM openjdk:8-jre-alpine
MAINTAINER Alex Pomosov
COPY build/libs/matchmaker-1.0-SNAPSHOT.jar /services/garbager.jar
EXPOSE 8080
CMD ["java", "-jar", "/services/garbager.jar"]