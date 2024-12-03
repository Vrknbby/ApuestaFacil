FROM maven:3.8-openjdk-21 as compile

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN mvn clean package -DskipTests -X

# Fase de ejecución
FROM openjdk:21

COPY --from=compile /usr/src/app/target/*.jar /usr/app/app.jar

WORKDIR /usr/app

# Comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
