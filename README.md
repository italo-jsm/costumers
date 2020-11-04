# Costumer data API
## Info
This project is a basic CRUD API for handling costumers of a hypothetical company.
It has all the basic operations on a database:
- Create a costumer
- Get costumers by cpf name and id
- Create costumers
- Update and patch costumers
## Required Tools
This project uses some build and quality tools.
### Build
### Docker
It can be built from the costumer folder with the command:
```
docker build -t costumer-api .
```
After that a docker image will be on your local repository.
### Java
```
mvn install
```
After that the jar package should be on the target folder.
## Run
To run this project make sure you have a mysql database running on localhost.
That can be done either with a mysql client running locally or in a docker container.
To run the database on docker use the command:
```
docker run -d -p 3306:3306 mysql
```
Make sure you have a database with the credentials listed on application.properties on your mysql server.
### Docker
```
docker run -d -p 8080:8080 costumer-api
```
### Java
```
java -jar "project-name"
```

## Use
There is a basic postman collection on this repository that can be used to check this API functionalities. 
## Code Quality
This project has been developed based on TDD. It has several unit tests.
The tests coverage and many other code quality information can be found using SonarQube.
To check the quality information make sure you have a SonarQube running locally and run these commands:
```
mvn clean install
mvn sonar:sonar
```
After that just check the local SonarQube page.
