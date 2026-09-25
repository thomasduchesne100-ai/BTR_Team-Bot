FROM maven:3.9.16-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/btr-team-bot-1.0.0.jar"]
