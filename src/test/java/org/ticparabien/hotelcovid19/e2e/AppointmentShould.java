package org.ticparabien.hotelcovid19.e2e;

import org.ticparabien.hotelcovid19.Configuration;
import org.ticparabien.hotelcovid19.domain.Credentials;
import org.ticparabien.hotelcovid19.domain.RoomService;
import org.ticparabien.hotelcovid19.helpers.IntegrationTests;
import org.ticparabien.hotelcovid19.helpers.Properties;
import org.ticparabien.hotelcovid19.helpers.TestFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"" +
                "spring.datasource.url=jdbc:postgresql://localhost:5432/" + Configuration.testDb + "?user=" + Configuration.dbUser + "&password=" + Configuration.dbPassword,
                "server.port=" + Properties.WEB_SERVER_PORT
        })
@RunWith(SpringJUnit4ClassRunner.class)
public class AppointmentShould extends IntegrationTests {

    private WebDriver driver;
    private RoomService RoomService = TestFactory.RoomService(Configuration.connectionTestDatabase);

    private WebDriver browser() {
        if (driver == null) {
            startChrome();
            //startFirefox();
        }
        return driver;
    }

    private void startFirefox() {
        driver = new FirefoxDriver();
    }

    private void startChrome() {
        System.setProperty("webdriver.chrome.driver", Configuration.chromeDriverPath);
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

    @Test
    public void be_made_by_the_owner() throws IOException, SQLException {
        createRoom();
        doWebLogin(Configuration.ownername, Configuration.webPassword);
        //make the appointment
        //assert the appointment is made
    }

    private void doWebLogin(String username, String password) {
        browser().get(Configuration.webUrl + Configuration.loginUrl);
        browser().findElement(By.name("username")).sendKeys(username);
        browser().findElement(By.name("password")).sendKeys(password);
        browser().findElement(By.name("submitLogin")).click();
        //TODO: assert that response status code is 200
    }

    private void createRoom() throws SQLException {
        Credentials webCredentials = new Credentials(Configuration.ownername, Configuration.webPassword);
        RoomService.create(Configuration.RoomName, webCredentials);
    }

    private void waitForElementWithId(String className, WebDriver browser) {
        WebDriverWait waiter = new WebDriverWait(browser, 5);
        waiter.until(presenceOfElementLocated(By.id(className)));
    }
}
