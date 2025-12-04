#IMAGEN MODELO
FROM eclipse-temurin:21.0.3_9-jdk
ARG JAR_FILE=target/crud-0.0.1.-jar
COPY ${JAR_FILE} app_crudmuebles.jar
EXPOSE 8080
ENTRYPOINT ["java","-JAR","app_crudmuebles.jar"]