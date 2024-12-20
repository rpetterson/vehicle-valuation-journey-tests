package steps;

import io.cucumber.java.en.Then;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.CarDetailsPage;
import utils.FileUtils;

public class CarDetailsSteps {
  private WebDriver driver;
  private CarDetailsPage carDetailsPage;
  private Map<String, Map<String, String>> expectedCarDetails;

  public CarDetailsSteps() {
    this.driver = Hooks.getDriver(); // Reuse the shared WebDriver instance
    this.carDetailsPage = new CarDetailsPage(driver);
  }

  @Then("I should see all car details match the expected values from {string}")
  public void all_car_details_should_match(String outputFileName) throws InterruptedException {
    Thread.sleep(2000);
    expectedCarDetails = FileUtils.readCarDetailsFile("src/test/resources/" + outputFileName);
    Assertions.assertThat(expectedCarDetails).isNotEmpty();

    List<String> validRegistrations = EnterRegistrationSteps.validRegistrations;

    // Actual values
    String actualRegistration = carDetailsPage.getRegistration();
    String regWithoutSpaceActualRegistration = actualRegistration.replaceAll("\\s", "");
    Map<String, String> expectedMakeAndModel =
        expectedCarDetails.get(regWithoutSpaceActualRegistration);

    String actualMakeModel = carDetailsPage.getMakeModel();
    String actualYear = carDetailsPage.getYear();

    // Expected values
    String expectedMakeModel = expectedMakeAndModel.get("MAKE_MODEL");
    String expectedYear = expectedMakeAndModel.get("YEAR");

    System.out.println("Actual Value: " + actualRegistration);
    System.out.println("Expected Value: " + validRegistrations.contains(actualRegistration));

    // Perform assertions
    Assertions.assertThat(validRegistrations.contains(regWithoutSpaceActualRegistration));
    Assertions.assertThat(actualMakeModel).isEqualToIgnoringWhitespace(expectedMakeModel);
    Assertions.assertThat(actualYear).isEqualToIgnoringWhitespace(expectedYear);

    // Go back to Search Page
    carDetailsPage.goBack();

    System.out.println("All registrations validated successfully.");
  }
}
