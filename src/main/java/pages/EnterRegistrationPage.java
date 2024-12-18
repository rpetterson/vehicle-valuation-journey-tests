package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EnterRegistrationPage {
    private WebDriver driver;


    private By registrationInput = By.id("vrm-input");
    private By valueCarButton = By.cssSelector("button[type='submit']");

    public EnterRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMotorwayHomePage() {
        driver.get("https://www.motorway.co.uk/");
    }

    public void enterRegistration(String registration) {
        driver.findElement(registrationInput).sendKeys(registration);
    }

    public void clickValueYourCar() {
        driver.findElement(valueCarButton).click();
    }
}
