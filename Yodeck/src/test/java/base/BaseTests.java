package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import testRunner.TestRunner;
import utils.ConfigReader;

public class BaseTests {
    protected static ChromeDriver driver;
    protected static LoginPage loginPage;
    protected static RegistrationPage registrationPage;
    protected static HomePage homePage;
    protected static WelcomePage welcomePage;
    protected static ImagePage imagePage;
    protected static ConfigReader configReader;
    protected static final Logger logger = LogManager.getLogger(BaseTests.class);

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.yodeck.com/");
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
        welcomePage = new WelcomePage(driver);
        imagePage = new ImagePage(driver);
        configReader = new ConfigReader(driver);
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}



