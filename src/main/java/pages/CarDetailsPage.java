package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarDetailsPage {
    private WebDriver driver;


    private By registrationLocator = By.cssSelector(".car-registration"); // Adjust selector
    private By makeModelLocator = By.cssSelector(".car-make-model"); // Adjust selector
    private By yearLocator = By.cssSelector(".car-year"); // Adjust selector

    public CarDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getRegistration() {
        return driver.findElement(registrationLocator).getText().trim();
    }

    public String getMakeModel() {
        return driver.findElement(makeModelLocator).getText().trim();
    }

    public String getYear() {
        return driver.findElement(yearLocator).getText().trim();
    }
}
