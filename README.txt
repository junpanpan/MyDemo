
------------------------------------------------
  Introduction
------------------------------------------------
It is a web application.


------------------------------------------------
  Design
------------------------------------------------
The application is built using Java, Spring Boot and Maven.


------------------------------------------------
  Pre-requisites
------------------------------------------------
The following must be installed in order to build and run the demo app:
  - Oracle Java SE Runtime Environment 11
  - Apache Maven 3

------------------------------------------------
  Configuration
------------------------------------------------


------------------------------------------------
  Build
------------------------------------------------
From the root project directory run the Maven command to create an executable Java Archive (JAR) in the Maven target directory.
mvn clean install

------------------------------------------------
  Run
------------------------------------------------
Navigate to the target folder and run the command from a command window:
cd trafik\target
java -jar .\trafik-1.0-SNAPSHOT.war

This will launch the application. Tomcat server is embedded in the JAR so you don't need to install an application server.

Point your browser to:
http://localhost:8080/bus/view

