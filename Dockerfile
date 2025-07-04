FROM maven:3.9.10-eclipse-temurin-21-alpine AS build
COPY . /home/app/contacts
RUN mvn -v
RUN mvn -f /home/app/contacts/pom.xml clean package


#Step 2 - Run backend
FROM alpine/java:21-jdk
COPY --from=build /home/app/contacts/target/*.jar /usr/local/lib/contacts.jar
VOLUME /tmp
EXPOSE 80:8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/contacts.jar"]
