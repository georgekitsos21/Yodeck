package stepDefinitions.registrationsteps;

import base.BaseTests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import utils.ConfigReader;

import static org.testng.AssertJUnit.assertEquals;

public class RegistrationSteps extends BaseTests {

    @Given("the user navigates to the registration page")
    public void navigateToRegistrationPage() {
        homePage.clickLetsStartButton();
        registrationPage.switchToNewTab("https://app.yodeck.com/signup");
    }

    @When("I accept cookies")
    public void acceptCookies() {
        registrationPage.clickToAcceptCookies();
    }

    @When("I enter email {string}")
    public void enterEmail(String email) {
        registrationPage.fillTheSignUpEmailField(email);
    }

    @When("I continue to the next step")
    public void clickContinue() {
        registrationPage.clickTheContinueBtn();
    }

    @When("I enter full name {string}")
    public void enterFullName(String fullName) {
        registrationPage.fillTheFullNameField(fullName);
    }

    @When("I enter password {string}")
    public void enterPassword(String password) {
        registrationPage.fillThePasswordField(password);
    }

    @When("I click on register button")
    public void clickRegister() {
        registrationPage.clickRegistrationBtn();
    }

    @Then("I should be successfully logged in")
    public void verifyRegistrationSuccess() {
        String expectedUrl = ConfigReader.getProperty("loginUrl");
        String actualUrl = driver.getCurrentUrl();
        assertEquals("The user is not on the expected success URL!", expectedUrl, actualUrl);
    }
}
