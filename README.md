# Region Manager

Service that manages information about regions.

## Summary
Region manager is service that provides API for information about regions. 
For now following methods are supported:
* get information about all regions
* get information about region by its id
* update information about region
* add region
* remove region

For more information about API, please use [API Documentation](./api.md).

## Requirements
For building and running this application you will need:
* [JDK 1.8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
* [Maven 3](https://maven.apache.org/)

## Running application locally
To run application locally one can use IDE by executing main method in class 
`com.github.helena128.regionmanager.RegionManagerApplication`.

Another way to run this app is to use `mvn spring-boot:run` in the command line.

Also it is possible to run application the following way:\
```mvn clean package && java -jar target/regionmanager-1.0-SNAPSHOT.jar```

By default application runs on port `8000` and has base path `/regionmanager/1.0`.

Sample request:
```
curl -i -X GET -H "Content-Type: application/json" -H "Accept: application/json" http://localhost:8000/regionmanager/1.0/regions
```
