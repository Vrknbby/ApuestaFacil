FROM maven:3.8.1-openjdk-22 as compile

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN mvn clean package -DskipTests -X

FROM openjdk:22

COPY --from=compile /usr/src/app/target/*.jar /usr/app/app.jar

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "app.jar"]