FROM eclipse-temurin:17.0.7_7-jdk

WORKDIR /app

COPY target/hr-management-0.0.1-SNAPSHOT hr-management.jar

CMD ["java", "-jar", "crm-app.jar"]
