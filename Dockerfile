FROM openjdk:17-slim
COPY build/libs/buddy.jar /app/buddy.jar
WORKDIR /app
CMD ["java", "-jar", "buddy.jar"]
