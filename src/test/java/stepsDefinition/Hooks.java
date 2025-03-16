package stepsDefinition;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Hooks extends DriverManager {

    @Before
    public void driverSetUp(Scenario scenario){
        ExtentCucumberAdapter.addTestStepLog("Starting test: " + scenario.getName());
        setUp();
    }

    @After
    public void quitDriver(Scenario scenario){
        if (scenario.isFailed()) {
            try {
                ExtentCucumberAdapter.addTestStepLog("❌ Test failed: " + scenario.getName());
                scenario.log("❌ Test failed, capturing screenshot...");

                // Capture Screenshot
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Test Screenshot");

                // Save Screenshot ke `target/screenshots/`
                Path screenshotDir = Paths.get("test-output/", "screenshots");
                Files.createDirectories(screenshotDir);
                String screenshotName = "screenshot_" + UUID.randomUUID() + ".png";
                Path screenshotPath = screenshotDir.resolve(screenshotName);
                Files.write(screenshotPath, screenshot);

                scenario.log("📸 Screenshot saved: " + screenshotPath.toAbsolutePath());

            } catch (Exception e) {
                ExtentCucumberAdapter.addTestStepLog("⚠️ Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            ExtentCucumberAdapter.addTestStepLog("✅ Test passed: " + scenario.getName());
        }

        tearDown();
        ExtentCucumberAdapter.addTestStepLog("🛑 WebDriver session closed.");
    }
}
