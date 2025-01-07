package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHelper {

    private final WebDriver driver;

    public ActionHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method to perform a double-click on a specified element.
     *
     * @param element The WebElement to double-click.
     */

    public void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }
}