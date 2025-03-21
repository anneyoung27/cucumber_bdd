package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

import static helper.PropertiesHelper.loadFile;

public class DriverFactory {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static Properties setUp = loadFile();
    public static WebDriverWait wait;

    public static String browser;

    public static Logger log = LogManager.getLogger();

    public static void driverSetUp() {
        if (getDriver() == null) {

            if (setUp.isEmpty()) {
                log.warn("Failed to load config.properties, using empty properties");
            } else {
                log.info("config.properties file has been loaded successfully");
            }

            if (System.getenv("BROWSER") != null && !System.getenv("BROWSER").isEmpty()) {
                browser = System.getenv("BROWSER");
            } else {
                browser = setUp.getProperty("BROWSER");
            }

            setUp.setProperty("BROWSER", browser);
            initializeDriver();

            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(setUp.getProperty("IMPLICIT_WAIT"))));
            getDriver().manage().window().maximize();
            getDriver().get(setUp.getProperty("URL"));
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        }
    }

    private static void initializeDriver() {
        switch (setUp.getProperty("BROWSER").toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new ChromeDriver());
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
        log.info("{} browser has been selected", setUp.getProperty("BROWSER"));
    }

    // this is used to get the driver with ThreadLocal
    public static synchronized WebDriver getDriver(){
        return driver.get();
    }

    public static void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
