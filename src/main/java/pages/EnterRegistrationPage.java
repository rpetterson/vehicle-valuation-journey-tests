package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class EnterRegistrationPage {
    private WebDriver driver;


    private By registrationInput = By.id("vrm-input");
    private By valueCarButton = By.cssSelector("button[type='submit']");

    private By errorMessageLocator = By.cssSelector("div.InfoBox-module__infoBox-JXzH.InfoBox-module__error-sQpY");


    public EnterRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMotorwayHomePage() {
        driver.get("https://www.motorway.co.uk/");
    }

    public void enterRegistration(String registration) {
        driver.findElement(registrationInput).clear();
        driver.findElement(registrationInput).sendKeys(registration);
    }

    public void clickValueYourCar() throws InterruptedException {
        driver.findElement(valueCarButton).click();
        Thread.sleep(2000);
    }

    // Method to check if error message is displayed
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessageLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText().trim();
    }

    public void clearRegistrationField() {
        driver.findElement(registrationInput).clear();
    }
}
