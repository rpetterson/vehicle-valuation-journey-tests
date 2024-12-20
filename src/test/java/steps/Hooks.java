package steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            driver = new ChromeDriver();
            System.out.println("Driver initialized.");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterAll
    public static void tearDown() {
        // Close the driver only once after all tests
        if (driver != null) {
            //driver.quit();
            System.out.println("Driver closed after all tests.");
        }
    }
}
