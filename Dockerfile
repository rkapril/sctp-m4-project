FROM eclipse-temurin:17.0.7_7-jdk

WORKDIR /app

COPY target/hr-management-0.0.1-SNAPSHOT.jar hr-management.jar

CMD ["java", "-jar", "hr-management.jar"]
