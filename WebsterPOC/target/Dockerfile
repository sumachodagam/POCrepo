FROM java:8

ADD WebsterPOC-0.0.1-SNAPSHOT.jar /opt/WebsterPOC/
EXPOSE 8080
WORKDIR /opt/WebsterPOC/
CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "WebsterPOC-0.0.1-SNAPSHOT.jar"]