package stepDefinitions.loginsteps;
import base.BaseTests;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import utils.ConfigReader;


public class LoginSteps extends BaseTests {
    protected static final Logger logger = LogManager.getLogger(LoginSteps.class);

    @When("I am on the login page")
    public void iLoginToTheLoginPage() {
        homePage.clickLogInButton();
        loginPage.switchToNewLoginTab("https://app.yodeck.com/login");
    }

    @When("I refresh the page")
    public void iRefreshThePage(){
        loginPage.refreshTheLoginPage();
    }

    @When("I enter a valid email {string}")
    public void iEnterAValidEmail(String email) {
        loginPage.fillTheEmail(email);
    }

    @When("I enter a invalid email {string}")
    public void iEnterInvalidEmail(String email) {
        loginPage.fillTheEmail(email);
    }

    @When("I click the continue button")
    public void iClickTheContinueButton() {
        loginPage.clickContinueButton();
    }

    @When("I enter a valid password {string}")
    public void iEnterAValidPassword(String password) {
        loginPage.fillThePassword(password);
    }

    @When("I enter a invalid password {string}")
    public void iEnterAInvalidPassword(String password) {
        loginPage.fillThePassword(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @When("I navigate to the first open tab")
    public void iNavigateToTheFirstTab() {
        welcomePage.switchToTheFirstTab("https://www.yodeck.com/");
        homePage.clickLogInButton();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        String expectedUrl = ConfigReader.getProperty("loginUrl");
        String actualUrl = getCurrentUrl();
        if (expectedUrl.equals(actualUrl)) {
            Assert.assertEquals("The user is not on the expected URL: ", actualUrl, expectedUrl);
        }
        else
            logger.error("The expected URL is not the same with the actual");
    }

    @Then("I should not be logged in successfully")
    public void iShouldBeNotLoggedInSuccessfully() {
        String expectedMessage = "incorrect credentials";
        String actualMessage = loginPage.getWrongCredentialsErrorMessage();
        Assert.assertEquals("The expected error message was not displayed", expectedMessage, actualMessage);
    }
}
