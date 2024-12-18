package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.EnterRegistrationPage;
import utils.FileUtils;

import java.util.List;

public class EnterRegistrationSteps {
    private WebDriver driver;
    private EnterRegistrationPage enterRegistrationPage;
    private List<String> validRegistrations;

    @Given("I load valid registrations from {string}")
    public void i_load_valid_registrations(String fileName) {
        validRegistrations = FileUtils.readValidRegistrations("src/test/resources/" + fileName);
        System.out.println("Loaded valid registrations: " + validRegistrations);
    }

    @When("I enter each valid registration and value the car")
    public void i_enter_each_valid_registration() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        enterRegistrationPage = new EnterRegistrationPage(driver);

        for (String registration : validRegistrations) {
            System.out.println("Processing registration: " + registration);
            enterRegistrationPage.openMotorwayHomePage();
            enterRegistrationPage.enterRegistration(registration);
            enterRegistrationPage.clickValueYourCar();
        }
    }

    public List<String> getValidRegistrations() {
        return validRegistrations;
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
