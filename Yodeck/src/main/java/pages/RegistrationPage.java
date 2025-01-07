package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitHelper;
import java.util.Objects;
import java.util.Set;

public class RegistrationPage {

    private final WebDriver driver;
    private final By preSignUpEmailRegistrationField = By.cssSelector("#pre-signup-email");
    private final By continueButton = By.cssSelector("#continue-user");
    private final By acceptCookies = By.id("CybotCookiebotDialogBodyButtonAccept");
    private final By fullNameField = By.id("signup_full_name");
    private final By passwordField = By.id("signup_password");
    private final By registrationButton = By.id("submit-user");
    private final WaitHelper waitHelper;

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 3);
    }

    public void fillTheSignUpEmailField(String email){
        waitHelper.waitForVisibilityOfElement(preSignUpEmailRegistrationField).sendKeys(email);
    }

    public void clickTheContinueBtn(){
        driver.findElement(continueButton).click();
    }

    public void switchToNewTab(String expectedUrl) {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                if (Objects.equals(driver.getCurrentUrl(), expectedUrl)) {
                    return;
                }
            }
        }
        throw new RuntimeException("No tab with URL containing: " + expectedUrl);
    }

    public void clickToAcceptCookies(){
        driver.findElement(acceptCookies).click();
    }

    public void fillTheFullNameField(String fullName){
        driver.findElement(fullNameField).sendKeys(fullName);
    }

    public void fillThePasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegistrationBtn(){
        driver.findElement(registrationButton).click();
    }
}
