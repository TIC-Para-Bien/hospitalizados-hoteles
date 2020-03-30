# Backend

## Start development environment

Windows:

```powershell
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

Once the app is running:

- You can have [autoreload](https://medium.com/@lhartikk/development-environment-in-spring-boot-with-docker-734ad6c50b34) when you are making changes in the app without doing a new `mvn clean install`.
- Follow [this guide](https://blog.jetbrains.com/idea/2019/04/debug-your-java-applications-in-docker-using-intellij-idea/) to enable remote debugging in the app.

Autoreload / hot swap guides:

- Intellij: Debug with Docker Compose <https://www.jetbrains.com/help/idea/run-and-debug-a-spring-boot-application-using-docker-compose.html>
- Docker intellij remote debug <https://www.docker.com/blog/spring-boot-development-docker/>
- Development Environment with spring-boot-devtools <https://medium.com/@lhartikk/development-environment-in-spring-boot-with-docker-734ad6c50b34>

The platform comes with a [dockerized desktop environment](platform/desktop/README.md).

## Production

- ```docker-compose up --build```

For deploying in production the service "hotel_covid_database_test" is irrelevant at this moment, 
we are not launching the test suites on building the app.

Take care about database volumes!

## STACK

### Java 11

### SpringBoot

### ORM

- Hibernate

### DB version control

- Flyway

### DB conennection pool

- Hikari

### DB test

- H2 (in memory DB)

### Other

- Lombok
```
Configuration for IntelliJ IDE
File Settings -> Plugins  -> lombok
File Settings -> Annotation Processor -> enable annotation processing
```

### QA

- Unit tests

    ```console
    mvn test
    ```

- Integration tests

    ```console
    mvn verify -P integration-test -Dtest=WTFTest -DfailIfNoTests=false
    ```

- Acceptance tests

    ```console
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

### CI/CD

- Jenkins (coming soon): <https://jenkins-covid.redpandaci.com>
