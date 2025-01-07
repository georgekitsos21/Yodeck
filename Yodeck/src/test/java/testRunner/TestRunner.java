package testRunner;

import base.BaseTests;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/features" }, glue = {
        "stepDefinitions",
        "hooks"},
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
        },
        monochrome = true,
        tags = "@Regression or @Images"
)

public class TestRunner extends BaseTests {
}



