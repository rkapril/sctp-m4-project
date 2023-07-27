FROM eclipse-temurin:17.0.7_7-jdk

WORKDIR /app

<<<<<<< HEAD
=======
#COPY target/simple-crm-0.0.1-SNAPSHOT.jar crm-app.jar
>>>>>>> afa427676c7dbdded512f1c842653e68c12e3a6c
COPY target/hr-management-0.0.1-SNAPSHOT.jar hr-management.jar

CMD ["java", "-jar", "hr-management.jar"]
