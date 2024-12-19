package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.EnterRegistrationPage;
import utils.FileUtils;
import org.assertj.core.api.Assertions;
import java.util.List;

public class EnterRegistrationSteps {
    public static WebDriver driver; // Static WebDriver instance
    private EnterRegistrationPage enterRegistrationPage;

    // Static list for valid registrations
    public static List<String> validRegistrations;

    @Given("I load valid registrations from {string}")
    public void i_load_valid_registrations(String fileName) {
        validRegistrations = FileUtils.readValidRegistrations("src/test/resources/" + fileName);
        System.out.println("Loaded valid registrations: " + validRegistrations);
    }

    @When("I enter each valid registration and value the car")
    public void i_enter_each_valid_registration() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        enterRegistrationPage = new EnterRegistrationPage(driver);

        for (String registration : validRegistrations) {
            System.out.println("Processing registration: " + registration);
            enterRegistrationPage.openMotorwayHomePage();
            enterRegistrationPage.enterRegistration(registration);
            enterRegistrationPage.clickValueYourCar();

            // Wait for the page to load (adjust time as necessary or use WebDriverWait)
            Thread.sleep(3000);

            // Check if error message is displayed for non-existent registration
            if (enterRegistrationPage.isErrorMessageDisplayed()) {
                System.out.println("Error message displayed for invalid registration: " + registration);

                // Assert the error message is correct
                String expectedErrorMessage = "We couldn’t find a vehicle with that registration. Enter a valid UK reg. Recent registrations take a few days to appear on our system.";
                Assertions.assertThat(enterRegistrationPage.getErrorMessage())
                        .contains(expectedErrorMessage);

                // Clear the input field for the next registration
                enterRegistrationPage.clearRegistrationField();
            } else {
                // Proceed with validations if the registration is valid
                System.out.println("Valid registration processed: " + registration);
            }

            // Add a delay to avoid rate limiting
            Thread.sleep(3000); // 3 seconds, adjust if necessary
        }
    }


    public static WebDriver getDriver() {
        return driver;
    }
}
