## Deploy backend

### Start development environment

Windows:

```shell
C:\workspace> cd backend
C:\workspace\backend> copy .env.dist .env
C:\workspace\backend> docker-compose up -d

[...]

```

Linux/Mac:

```shell
$ cd backend
$ cp .env.dist .env
$ docker-compose up -d

[...]

```

Wait for the environment to get ready. You can view the progress of the starting proccess executing `docker-compose logs -f`

Once the app is running, follow [this guide](https://blog.jetbrains.com/idea/2019/04/debug-your-java-applications-in-docker-using-intellij-idea/) to enable remote debugging in the app.
 
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
