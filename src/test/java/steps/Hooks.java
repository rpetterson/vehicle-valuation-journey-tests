package steps;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        // Initialize the WebDriver once for all scenarios
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        System.out.println("Driver initialized once for all scenarios.");
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterAll
    public static void tearDown() {
        // Quit the driver after all scenarios
        if (driver != null) {
            driver.quit();
            System.out.println("Driver closed after all scenarios.");
        }
    }
}

