FROM openjdk:13
EXPOSE 8080
ADD ./target/analysis-1.0.jar target.jar
ENTRYPOINT ["java","-jar","target.jar"]