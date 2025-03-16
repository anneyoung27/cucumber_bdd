package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = {"stepsDefinition"},
        tags = "(@Login or @AddProduct) or @Checkout",
        plugin = {
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty"
        },
        monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}
