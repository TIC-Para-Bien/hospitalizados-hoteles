## Deploy backend

### Develop
 - ```docker-compose up --build```
 
### Production
  - ```docker-compose up --build```

For deploying in production the service "hotel_covid_database_test" is irrelevant at this moment, 
we are not launching the test suites on building the app.

Take care about database volumes!


### STACK

#### Java 11

#### SpringBoot

#### ORM
- Hibernate

#### DB version control
- Flyway

#### DB conennection pool
- Hikari

#### DB test
- H2 (in memory DB)

#### Other
- Lombok

#### QA

- Unit tests

    ```
    mvn test
    ```

- Integration tests

    ```
    mvn verify -P integration-test -Dtest=WTFTest -DfailIfNoTests=false
    ```

- Acceptance tests

    ```
    mvn verify -P acceptance-test -Dtest=WTFTest -DfailIfNoTests=false
    ```
- Smoke tests (coming soon)

- Code coverage
    - JaCoCo
    
- Static Analysis
    - IntelliJ built-in analyzer
    - SonarLint (local) -> Install IntelliJ plugin!!
    - Sonarqube (remote) (coming soon)
    
- Dependency vulnerability analysis
    - Snyk (coming soon)

#### CI/CD

- Jenkins (coming soon)
