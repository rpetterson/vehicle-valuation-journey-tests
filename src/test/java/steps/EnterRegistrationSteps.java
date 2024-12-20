package steps;

import io.cucumber.java.en.*;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.WebDriver;
import pages.CarDetailsPage;
import pages.EnterRegistrationPage;
import utils.FileUtils;
import org.assertj.core.api.Assertions;

import java.util.List;

public class EnterRegistrationSteps {
    private WebDriver driver; // Static WebDriver instance
    private EnterRegistrationPage enterRegistrationPage;
    private CarDetailsPage carDetailsPage;
    // Static list for valid registrations
    public static List<String> validRegistrations;
    public EnterRegistrationSteps() {
        // Use the shared driver from Hooks
        this.driver = Hooks.getDriver();
    }
    @Given("I load valid registrations from {string}")
    public void i_load_valid_registrations(String fileName) {
        validRegistrations = FileUtils.readValidRegistrations("src/test/resources/" + fileName);
        System.out.println("Loaded valid registrations: " + validRegistrations);
    }

    @When("I enter each valid registration and value the car")
    public void i_enter_each_valid_registration() throws InterruptedException {
        enterRegistrationPage = new EnterRegistrationPage(driver);
        carDetailsPage = new CarDetailsPage(driver);

        for (String registration : validRegistrations) {
            if(StringUtils.isNotBlank(registration))processRegistration(registration);
        }


    }

    private void processRegistration(String registration) throws InterruptedException {
        System.out.println("Processing registration: " + registration);
        enterRegistrationPage.openMotorwayHomePage();
        enterRegistrationPage.enterRegistration(registration);
        enterRegistrationPage.clickValueYourCar();

        Thread.sleep(3000);

        System.out.println("Current URL: " + driver.getCurrentUrl());

        if (enterRegistrationPage.isErrorMessageDisplayed()) {
            handleInvalidRegistration(registration);
        } else {
            handleValidRegistration(registration);
        }

        // Add a delay to avoid rate limiting on live service
        Thread.sleep(3000);
    }

    private void handleInvalidRegistration(String registration) {
        System.out.println("Error message displayed for invalid registration: " + registration);

        String actualErrorMessage = enterRegistrationPage.getErrorMessage();
        String expectedErrorMessage = "We couldn’t find a vehicle with that registration. Enter a valid UK reg. Recent registrations take a few days to appear on our system.";

        Assertions.assertThat(actualErrorMessage.trim())
                .contains(expectedErrorMessage);

        enterRegistrationPage.clearRegistrationField();
    }

    private void handleValidRegistration(String registration) {
        // Log the current URL for debugging purposes
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Retrieve the actual registration from the CarDetailsPage
        String actualRegistration = carDetailsPage.getRegistration();

        // Log expected and actual registration values for debugging
        System.out.println("Expected Registration: " + registration);
        System.out.println("Actual Registration: " + actualRegistration);

        // Assertion to validate registration matches
        Assertions.assertThat(actualRegistration.replaceAll("\\s", ""))
                .isEqualToIgnoringWhitespace(registration.replaceAll("\\s", ""));


        System.out.println("Valid registration processed successfully: " + registration);
    }
}
