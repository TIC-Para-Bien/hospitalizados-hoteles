## Deploy backend

### Develop
 - ```docker-compose up --build```
 
 ### Production
  - ```docker-compose up --build```

For deploying in production the service "hotel_covid_database_test" is irrelevant at this moment, 
we are not launching the test suites on building the app.

Take care about database volumes!


### STACK
- Lombok

#### DB version control
- Flyway

#### DB test
- H2 (in memory DB)