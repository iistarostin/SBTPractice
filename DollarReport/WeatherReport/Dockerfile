FROM openjdk:13
EXPOSE 8080
ADD ./target/weatherreport-1.0-exec.jar target.jar
ENTRYPOINT ["java","-jar","target.jar", "--server.port=8080", "--spring.datasource.driver-class-name=org.postgresql.Driver", "--spring.datasource.username=postgres", "--spring.datasource.password=111","--spring.datasource.url=jdbc:postgresql://db:5432/postgres"]