FROM openjdk:11
VOLUME /tmp
COPY target/*.jar autocomplete-backend.jar
ENTRYPOINT ["java","-jar","/autocomplete-backend.jar"]
EXPOSE 8080