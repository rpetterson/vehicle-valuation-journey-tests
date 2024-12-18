Feature: Validate car details on the summary page

  Scenario: Verify valid car registrations and details
    Given I load valid registrations from "car_input-V5.txt"
    When I enter each valid registration and value the car
    Then I should see all car details match the expected values from "car_output_V5.txt"
