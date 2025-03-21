package stepsDefinition;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import factory.DriverFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {
    @Before (order = 0)
    public void getScenarioName(Scenario scenario){
        ExtentCucumberAdapter.addTestStepLog("Starting test: " + scenario.getName());
    }

    @Before (order = 1)
    public void setUp(Scenario scenario){
        DriverFactory.driverSetUp();
    }

    @After(order = 0)
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()) {
            try {
                ExtentCucumberAdapter.addTestStepLog("❌ Test failed: " + scenario.getName());
                scenario.log("❌ Test failed, capturing screenshot...");

                // Capture Screenshot
                byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);

                // Save Screenshot to `target/screenshots/`
                Path screenshotDir = Paths.get("test-output/", "screenshots");
                Files.createDirectories(screenshotDir);

                String fileName = "screenshot_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

                String screenshotName = "screenshot_" + fileName + ".png";
                Path screenshotPath = screenshotDir.resolve(screenshotName);

                Files.write(screenshotPath, screenshot);

                scenario.log("📸 Screenshot saved at: " + screenshotPath.toAbsolutePath());
            } catch (Exception e) {
                ExtentCucumberAdapter.addTestStepLog("⚠️ Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            ExtentCucumberAdapter.addTestStepLog("✅ Test passed: " + scenario.getName());
        }
        DriverFactory.tearDown();
        ExtentCucumberAdapter.addTestStepLog("🛑 WebDriver session closed.");
    }
}
