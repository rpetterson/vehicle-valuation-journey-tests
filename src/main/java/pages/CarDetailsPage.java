package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarDetailsPage {
    private WebDriver driver;


    private By registrationLocator = By.xpath("//*[@id=\"main\"]/div[1]/div[3]/div/div[2]/div/div/div/div[1]/div");
    private By makeModelLocator = By.xpath("//*[@id=\"main\"]/div[1]/div[3]/div/div[2]/div/div/div/div[1]/h1");
    private By yearLocator = By.cssSelector("#main > div.Hero__homepageHeroWrapper-tQXt.mileageTransitionStyles > div.Hero__homepageHero-XjVA > div > div:nth-child(2) > div > div > div > div.HeroVehicle__component-Av9f > ul > li:nth-child(1)");

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
