FROM openjdk:13
EXPOSE 8080
ADD ./target/analysis-1.0.jar target.jar
ENTRYPOINT ["java","-jar","target.jar", "--server.port=8080", "--app.network.weather-service-u-r-l=http://weather:8080", "--app.network.dollar-service-u-r-l=http://dollar:8080"]