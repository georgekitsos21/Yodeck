@Yodeck
Feature: User Registration

  Background:
    Given the user navigates to the registration page

  Scenario: Successful Registration
    And I refresh the page
    When I accept cookies
    And I enter email "georgekitsccscostest@yodeck114551.com"
    And I continue to the next step
    And I enter full name "George"
    And I enter password "Test@123"
    And I click on register button
    Then I should be successfully logged in