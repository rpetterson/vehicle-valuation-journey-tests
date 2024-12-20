package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarDetailsPage {
    private WebDriver driver;


    private By registrationLocator = By.cssSelector(".HeroVehicle__vrmBadgeContainer-OoPE > .VRM-module__vrm-hdeF[data-thc-badge-regular='true']");

    private By makeModelLocator = By.cssSelector("h1.HeroVehicle__title-FAmG[data-cy='vehicleMakeAndModel']");

    private By yearLocator = By.cssSelector(".HeroVehicle__component-Av9f ul > li:first-child");

    private By backLink = By.cssSelector("[data-cy='notMyCar']");
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

    public void goBack() {
        driver.findElement(backLink).click();
    }
}
