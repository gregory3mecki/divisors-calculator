# divisors-calculator

The `divisors-calculator` is a backend application which gives possibility to count divisors for given input numbers and map them to related words determined by used mapping.
It is written based on the RESTful model.

Additional tasks:
- It is initialized with 10 generated mappings.
- Words in each mapping are unique.
- The input numbers must be in the range [0-20].
- It is possible to add and remove mappings.

## Tech stack
The application is based on the technologies listed below:
- Java 17+
- Quarkus 2.16.3.Final
- Maven 3.8.6
- Guava 31.1-jre
- MapStruct 1.5.3.Final
- Lombok 1.18.26

## Modules
The project has been divided into the following modules.

### [divisors-calculator-application](divisors-calculator-application)

This module has base application configuration- quarkus dev, health check logic etc.

### [divisors-calculator-bom](divisors-calculator-bom)

Contains POM file with dependencies for all modules. 

### [divisors-calculator-core](divisors-calculator-core)

It is module with an elementary logic- it has a specific logic like divisors finding algorithm or REST utilities.

### [divisors-calculator-mapping](divisors-calculator-mapping)

Here are available all mapping tools, the logic which gives possibility to get/add/remove mappings.

### [divisors-calculator-rest](divisors-calculator-rest)

The implementation of REST API interfaces for mappings and calculator.

### [divisors-calculator-rest-api](divisors-calculator-rest-api)

Module contains REST API interfaces.

## The application in dev mode

Run application in dev mode that enables live coding using:
```shell script
./mvnw clean install -DskipTests
./mvnw compile quarkus:dev -pl divisors-calculator-application
```

Application is going to be available under http://localhost:8080.

Swagger documentation is available under http://localhost:8080/q/swagger-ui.

Health check is available here http://localhost:8080/q/health.

## Example requests

Below are some sample requests based on cURL.

### Divisors calculation

Count divisors for input numbers 8 and 20 mapped with words from mapping `mapping1`.

```
curl --request GET \
  --url 'http://localhost:8080/calculator-api/dividers/mappings/mapping1?number=8&number=20'
```

### Mappings manipulation

Get mapping with name `mapping1`.
```
curl --request GET \
--url http://localhost:8080/mapping-management/mappings/mapping1
```

Create mapping with name `mapping1`.

```
curl --request POST \
  --url http://localhost:8080/mapping-management/mappings \
  --header 'Content-Type: application/json' \
  --data '{
	"mappings": {
		"1": "val1",
		"2": "val2",
		"3": "val3"
	},
	"name": "mapping1"
}'
```

Remove mapping with name `mapping1`.

```
curl --request DELETE \
  --url http://localhost:8080/mapping-management/mappings/mapping1
```