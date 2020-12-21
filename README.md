# SimpleCalculatorApi

Simple Calculator Aegon Assessment

## Run

Use docker to start a postgres database

```bash
docker run -d \
    --name postgres-simple-Calculator \
    -e POSTGRES_PASSWORD=correcthorsebatterystaple \
    postgres
```

Use the same password in src/main/resources/application.properties

