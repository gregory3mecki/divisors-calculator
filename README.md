# divisors-calculator


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw clean install -DskipTests
./mvnw compile quarkus:dev -pl divisors-calculator-rest
```
Swagger documentation
```
/q/swagger-ui 
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.
