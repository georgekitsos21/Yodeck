package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;
import utils.WindowManager;
import java.util.Objects;
import java.util.Set;

public class LoginPage {
    private final By emailField = By.cssSelector("#email");
    private final By preLoginButton = By.cssSelector("#prelogin_button");
    private final By passwordField = By.cssSelector("#password");
    private final By loginButton = By.id("login_button");
    private final By incorrectCredentialsErrorMessage = By.cssSelector(".error-message.password_error");
    private final By menuContainer = By.cssSelector(".dropdown-toggle .ds-profile-user");
    private final By logOutBtn = By.cssSelector("#logOut");
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final WindowManager windowManager;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 5);
        this.windowManager = new WindowManager(driver);
    }

    public void fillTheEmail(String email) {
        WebElement emailElement = driver.findElement(emailField);
        waitHelper.waitForVisibilityOfElement(emailField).click();
        String fieldValue = emailElement.getDomProperty("value");

        if (fieldValue == null || fieldValue.isEmpty()) {
            emailElement.sendKeys(email);
            logger.info("Email entered: {}", email);
        } else {
            emailElement.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
            emailElement.sendKeys(email);
            logger.info("Email field cleared and new email entered: {}", email);
        }
    }

    public void clickContinueButton() {
        driver.findElement(preLoginButton).click();
        logger.info("Clicked on 'Continue' button.");
    }

    public void fillThePassword(String password) {
        waitHelper.waitForVisibilityOfElement(passwordField).sendKeys(password);
        logger.info("Password entered.");
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        logger.info("Clicked on 'Login' button.");
    }

    public void switchToNewLoginTab(String expectedUrl) {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        logger.info("Switching to the new login tab with URL: {}", expectedUrl);

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                waitHelper.waitForUrlToBe((expectedUrl));

                if (Objects.equals(driver.getCurrentUrl(), expectedUrl)) {
                    logger.info("Successfully switched to the new tab with URL: {}", expectedUrl);
                    return;
                }
            }
        }

        logger.error("Failed to switch to the new login tab.");
    }

    public void logOutFromTheParticularUser() {
        waitHelper.waitForVisibilityOfElement(menuContainer).click();
        logger.info("Clicked on user menu for logout.");

        waitHelper.waitForVisibilityOfElement(logOutBtn).click();
        logger.info("Clicked on 'Log Out' button.");
    }

    public String getWrongCredentialsErrorMessage() {
        WebElement errorElement = waitHelper.waitForVisibilityOfElement(incorrectCredentialsErrorMessage);
        String errorMessage = errorElement.getText();
        logger.error("Login failed with error: {}", errorMessage);
        return errorMessage;
    }

    public void refreshTheLoginPage(){
        windowManager.refreshPage();
        logger.info("Page refreshed.");
    }
}
