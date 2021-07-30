FROM openjdk:8-jdk-alpine
LABEL MANTAINER="fathyelshemy8@gmail.com"
EXPOSE 8080
COPY /target/gateway-peripheral-manager-0.0.1-SNAPSHOT.jar gateway-peripheral-manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","gateway-peripheral-manager-0.0.1-SNAPSHOT.jar"]