package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitHelper;
import java.util.Objects;
import java.util.Set;

public class HomePage {
    private final WebDriver driver;
    private final By loginButton = By.cssSelector(".main-navigation__login-link");
    private final By getStartedButton = By.cssSelector(".main-navigation__cta-link");
    private final  WaitHelper waitHelper;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 5);
    }

    public void clickLetsStartButton(){
        waitHelper.waitForVisibilityOfElement(getStartedButton).click();
    }

    public void clickLogInButton(){
        waitHelper.waitForVisibilityOfElement(loginButton).click();
    }

    public void switchToNewLoginTab(String expectedUrl) {
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
    }
}
