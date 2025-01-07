package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitHelper;
import java.util.Objects;
import java.util.Set;

public class WelcomePage {
    private final WebDriver driver;
    private final WaitHelper waitHelper;
    private final By mediaButton = By.cssSelector("#media_menu");
    private final By imagesSubLink = By.id("media_images");

    public WelcomePage(WebDriver driver){
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 15);
    }

    public String getSuccessURL(String url){
        driver.get("https://app.yodeck.com/index.html#main/welcome");
        return url;
    }

    public void clickMediaMenuAndOpenImages(){
        waitHelper.waitForVisibilityOfElement(mediaButton).click();
        waitHelper.waitForVisibilityOfElement(imagesSubLink).click();
    }

    public void switchToTheFirstTab(String expectedUrl) {
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

