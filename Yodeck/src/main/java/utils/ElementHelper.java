package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementHelper {
    private final WebDriver driver;

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findFirstVisibleElement(By... locators) {
        for (By locator : locators) {
            if (!driver.findElements(locator).isEmpty()) {
                return driver.findElement(locator);
            }
        }
        return null;
    }
}