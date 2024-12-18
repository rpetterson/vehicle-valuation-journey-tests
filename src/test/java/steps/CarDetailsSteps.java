package steps;

import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.CarDetailsPage;
import utils.FileUtils;

import java.util.List;
import java.util.Map;

public class CarDetailsSteps {

    private WebDriver driver;
    private CarDetailsPage carDetailsPage;
    private Map<String, Map<String, String>> expectedCarDetails;

    public CarDetailsSteps() {
        this.driver = new EnterRegistrationSteps().getDriver(); // Reuse driver
        this.carDetailsPage = new CarDetailsPage(driver);
    }

    @Then("I should see all car details match the expected values from {string}")
    public void all_car_details_should_match(String outputFileName) {
        expectedCarDetails = FileUtils.readCarDetailsFile("src/test/resources/" + outputFileName);
        Assertions.assertThat(expectedCarDetails).isNotEmpty();

        EnterRegistrationSteps enterSteps = new EnterRegistrationSteps();
        List<String> validRegistrations = enterSteps.getValidRegistrations();

        for (String registration : validRegistrations) {
            System.out.println("Validating details for registration: " + registration);

            String actualRegistration = carDetailsPage.getRegistration();
            String actualMakeModel = carDetailsPage.getMakeModel();
            String actualYear = carDetailsPage.getYear();

            Map<String, String> expectedDetails = expectedCarDetails.get(registration);
            Assertions.assertThat(expectedDetails).as("Expected details not found for registration " + registration).isNotNull();

            Assertions.assertThat(actualRegistration).isEqualToIgnoringWhitespace(registration);
            Assertions.assertThat(actualMakeModel).isEqualToIgnoringWhitespace(expectedDetails.get("MAKE_MODEL"));
            Assertions.assertThat(actualYear).isEqualToIgnoringWhitespace(expectedDetails.get("YEAR"));

            System.out.println("Details validated successfully for: " + registration);
        }
        System.out.println("All registrations validated successfully.");
    }
}
