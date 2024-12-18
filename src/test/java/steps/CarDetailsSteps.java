package steps;

import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.CarDetailsPage;
import utils.FileUtils;
import java.util.Map;

public class CarDetailsSteps {

    private WebDriver driver;
    private CarDetailsPage carDetailsPage;
    private Map<String, Map<String, String>> expectedCarDetails;

    public CarDetailsSteps() {
        this.driver = new EnterRegistrationSteps().getDriver();
        this.carDetailsPage = new CarDetailsPage(driver);
    }

    @Then("I should see all car details match the expected values from {string}")
    public void all_car_details_should_match(String outputFileName) {
        expectedCarDetails = FileUtils.readCarDetailsFile("src/test/resources/" + outputFileName);
        Assertions.assertThat(expectedCarDetails).isNotEmpty();

        EnterRegistrationSteps enterRegistrationSteps = new EnterRegistrationSteps();
        for (String registration : enterRegistrationSteps.getValidRegistrations()) {

            String actualRegistration = carDetailsPage.getRegistration();
            String actualMakeModel = carDetailsPage.getMakeModel();
            String actualYear = carDetailsPage.getYear();

            Map<String, String> expectedDetails = expectedCarDetails.get(registration);
            Assertions.assertThat(expectedDetails).isNotNull();

            Assertions.assertThat(actualRegistration).isEqualToIgnoringWhitespace(registration);
            Assertions.assertThat(actualMakeModel).isEqualToIgnoringWhitespace(expectedDetails.get("MAKE_MODEL"));
            Assertions.assertThat(actualYear).isEqualToIgnoringWhitespace(expectedDetails.get("YEAR"));
        }
    }
}
