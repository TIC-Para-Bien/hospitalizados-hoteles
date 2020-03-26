package org.ticparabien.hotelcovid19;

import org.ticparabien.hotelcovid19.helpers.Properties;

public class Configuration {
    public static final String webUrl = "http://localhost:" + Properties.WEB_SERVER_PORT;
    public static final String loginUrl = "/";
    public static final String appointmentsUrl = "/appointments";
    public static final String ownername = "hotelcovid19@ticparabien.org";
    public static final String webPassword = "12345";
    public static final String RoomName = "Superbarbers";
    public static final String chromeDriverPath = "./src/test/chromedriver";
    public static final String dbUser = "hotelcovid19";
    public static final String dbPassword = "12345";
    public static final String connectionBaseUrl = "jdbc:postgresql://localhost:5432/";
    public static final String testDb = "test";
    public static final String connectionTestDatabase = connectionBaseUrl + testDb + "?user=" + dbUser + "&password=" + dbPassword;
    public static final String productionDb = "hotelcovid19";
}
