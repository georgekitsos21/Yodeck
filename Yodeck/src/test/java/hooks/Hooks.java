package hooks;
import base.BaseTests;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Hooks extends BaseTests {
    protected static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Hooks.class);

    @After
    public void takeScreenshot(Scenario scenario) {

        if (scenario.isFailed()) {
            logger.info("Scenario '{}' has failed. Taking screenshot...", scenario.getName());

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {

                String screenshotPath = "resources/screenshots/" + scenario.getName() + "_failure.png";
                FileUtils.copyFile(screenshot, new File(screenshotPath));

                logger.info("Screenshot taken for failed scenario: {}. Saved to: {}", scenario.getName(), screenshotPath);

                byte[] screenshotBytes = Files.readAllBytes(screenshot.toPath());
                scenario.attach(screenshotBytes, "image/png", scenario.getName());

            } catch (IOException e) {
                logger.error("Failed to take screenshot for scenario: {}", scenario.getName(), e);
            }
        } else {
            logger.info("Scenario '{}' passed. No screenshot needed.", scenario.getName());
        }
    }

    @After("@SuccessfullyLogin")
    public void logOutFromTheParticularUser() {
        loginPage.logOutFromTheParticularUser();
    }

    @After("@ImageImportFromURL")
    public void deletePhotos(){
        imagePage.deletePhotos();
    }
}
