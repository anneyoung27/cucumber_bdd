package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static helper.PropertiesHelper.loadFile;

public class DriverFactory {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static Properties setUp = loadFile();
    public static WebDriverWait wait;

    public static String browser;

    public static Logger log = LogManager.getLogger();

    public static void driverSetUp() {
        if (getDriver() == null) {
            try{
                if (setUp.isEmpty()) {
                    log.warn("Failed to load config.properties, using empty properties");
                } else {
                    log.info("config.properties file has been loaded successfully");
                }

                browser = System.getenv("BROWSER") != null && !System.getenv("BROWSER").isEmpty()
                        ? System.getenv("BROWSER")
                        : setUp.getProperty("BROWSER");

                setUp.setProperty("BROWSER", browser);
                initializeDriver();

                WebDriver driverInstance = getDriver();

                driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(setUp.getProperty("IMPLICIT_WAIT"))));
                driverInstance.manage().window().maximize();
                driverInstance.get(setUp.getProperty("URL"));

                wait = new WebDriverWait(driverInstance, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
            } catch (RuntimeException e) {
                log.error("Error during driver setup: ", e);
                throw new RuntimeException("Driver setup failed!", e);
            }
        }
    }

    private static void initializeDriver() {
        try{
            switch (setUp.getProperty("BROWSER").toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;
                default:
                    throw new RuntimeException("Invalid browser specified in configuration: " + setUp.getProperty("BROWSER"));
            }
        }catch (UnreachableBrowserException e){
            log.error(e);
        }

        log.info("{} browser has been selected", setUp.getProperty("BROWSER"));
    }

    // this is used to get the driver with ThreadLocal
    public static synchronized WebDriver getDriver(){
        return driver.get();
    }

    public static void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            // driver = null;
            driver.remove(); // remove the driver from ThreadLocal
        }
    }
}
