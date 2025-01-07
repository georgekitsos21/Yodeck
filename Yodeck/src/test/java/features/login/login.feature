@Login @Yodeck

Feature: User Login
  As a user
  I want to log in to my account
  So that I can access the application features

  @SuccessfullyLogin @Regression
  Scenario: Successful login with valid credentials
    When I am on the login page
    And I accept cookies
    And I enter a valid email "kitsos212@test.com"
    And I click the continue button
    And I enter a valid password "Marcfit21@"
    And I click the login button
    Then I should be logged in successfully

  @Regression
  Scenario: UnSuccessful login with invalid email
    And I refresh the page
    And I enter a invalid email "kitsos21@invalidemail.com"
    And I click the continue button
    And I enter a valid password "Marcfit21@"
    And I click the login button
    Then I should not be logged in successfully

  @Regression
  Scenario: UnSuccessful login with invalid password
    And I refresh the page
    And I enter a valid email "kitsos212@test.com"
    And I click the continue button
    And I enter a invalid password "InvalidPassword"
    And I click the login button
    Then I should not be logged in successfully
