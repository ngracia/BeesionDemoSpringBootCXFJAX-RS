Demo

A Basic Spring Boot CXF JAX-RS

Prerequisites

You will need to following tools in order to work with this project and code


JDK 1.8+
Maven 3.x+
An IDE of your choice. (Eclipse, IntelliJ, Spring STS, Netbeans, etc.)

Getting Started

To run this project locally, perform the following steps.

Clone project to your machine using git. 
Import the project into your IDE using the maven pom.xml.
Run as Spring boot app or generate a war with maven and copy it on your tomcat webapps folder.

URLs (Running directly as Spring boot app)

http://localhost:8080/serviciosBeesion/sort?elements=5,3,4,2(GET)(Content-Type  text/plain)(Accept application/json , application/xml , application/text)
http://localhost:8080/serviciosBeesion/add?value=1:test4(POST) (Content-Type  text/plain)(Accept application/json , application/xml , application/text)
http://localhost:8080/serviciosBeesion/get?value=1(GET)(Content-Type  text/plain)(Accept application/json , application/xml , application/text)
http://localhost:8080/serviciosBeesion/jar(GET)(Accept application/octet-stream)


URLs (Running from Tomcat)

http://localhost:8080/servicios/serviciosBeesion/sort?elements=5,3,4,2(GET)(Content-Type  text/plain)(Accept application/json , application/xml , application/text)

http://localhost:8080/servicios/serviciosBeesion/add?value=1:test4(POST)(Content-Type  text/plain)(Accept application/json , application/xml , application/text)

http://localhost:8080/servicios/erviciosBeesion/get?value=1(Content-Type  text/plain)(Accept application/json , application/xml , application/text)

http://localhost:8080/servicios/serviciosBeesion/jar(GET)(Accept application/octet-stream)

