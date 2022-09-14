# Person Data Storage system

## Prerequisites
Java 11
<br />
Angular CLI: 14.2.2
<br />
Node: 16.17.0

## Build
Build:
<br />
mvn clean install

## Run Backend
Navigate:
<br />
cd pds-core
<br />
Run:
<br />
mvn spring-boot:run

## DB
http://localhost:8081/h2-console/
<br />
JDBC URL: jdbc:h2:mem:testdb
<br />
username: test
<br />
Password: test

## Swagger
http://localhost:8081/swagger-ui/

## UI
Navigate:
<br />
cd pds-core\src\main\resources\webapp\person-app
<br />
Run:
<br />
ng serve

http://localhost:4200