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
        this.driver = EnterRegistrationSteps.getDriver(); // Reuse the shared WebDriver instance
        this.carDetailsPage = new CarDetailsPage(driver);
    }

    @Then("I should see all car details match the expected values from {string}")
    public void all_car_details_should_match(String outputFileName) throws InterruptedException {
        Thread.sleep(2000);
        expectedCarDetails = FileUtils.readCarDetailsFile("src/test/resources/" + outputFileName);
        Assertions.assertThat(expectedCarDetails).isNotEmpty();

        List<String> validRegistrations = EnterRegistrationSteps.validRegistrations;

        for (String registration : validRegistrations) {
            System.out.println("Validating details for registration: " + registration);

            // Actual values
            String actualRegistration = carDetailsPage.getRegistration();
            Assertions.assertThat(actualRegistration.trim())
                    .isEqualToIgnoringWhitespace(registration.trim());


            String actualMakeModel = carDetailsPage.getMakeModel();
            String actualYear = carDetailsPage.getYear();

            // Expected values
            Map<String, String> expectedDetails = expectedCarDetails.get(registration);
            System.out.println("XXXXXXXXX expected all details" + expectedDetails);
            Assertions.assertThat(expectedDetails)
                    .as("Expected details not found for registration " + registration)
                    .isNotNull();


            String expectedMakeModel = expectedDetails.get("MAKE_MODEL");
            String expectedYear = expectedDetails.get("YEAR");

            System.out.println("Actual Value: " + actualRegistration);
            System.out.println("Expected Value: " + registration);

            // Perform assertions
            Assertions.assertThat(actualRegistration).isEqualToIgnoringWhitespace(registration);
            Assertions.assertThat(actualMakeModel).isEqualToIgnoringWhitespace(expectedMakeModel);
            Assertions.assertThat(actualYear).isEqualToIgnoringWhitespace(expectedYear);

            System.out.println("Details validated successfully for: " + registration);

            //Go back to Search Page
            carDetailsPage.goBack();
        }

        System.out.println("All registrations validated successfully.");
    }
}
