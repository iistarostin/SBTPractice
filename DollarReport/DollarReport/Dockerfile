FROM openjdk:13
EXPOSE 8080
ADD ./target/dollarreport-1.0-exec.jar target.jar
ENTRYPOINT ["java","-jar","target.jar"]