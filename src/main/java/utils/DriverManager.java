package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverManager {
    public static WebDriver driver;
    public static Properties setUp = new Properties();
    public static FileInputStream fis;
    public static WebDriverWait wait;

    public static String browser;

    public static Logger log = LogManager.getLogger();

    public static void setUp() {
        if (driver == null) {
            try {
                fis = new FileInputStream(
                        System.getProperty("user.dir") + "\\src\\main\\resources\\config\\config.properties");
                log.info("config.properties file has been found");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                setUp.load(fis);
                log.info("config.properties file has been loaded");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (System.getenv("BROWSER") != null && !System.getenv("BROWSER").isEmpty()) {
                browser = System.getenv("BROWSER");

            } else {
                browser = setUp.getProperty("BROWSER");
            }

            setUp.setProperty("BROWSER", browser);
            initializeDriver();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(setUp.getProperty("IMPLICIT_WAIT"))));
            driver.manage().window().maximize();
            driver.get(setUp.getProperty("URL"));
            wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        }
    }

    private static void initializeDriver() {
        switch (setUp.getProperty("BROWSER").toLowerCase()){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser specified in configuration: " + setUp.getProperty("BROWSER"));
        }
        log.info("{} browser has been selected", setUp.getProperty("BROWSER"));
    }

    public static void tearDown(){
        if (driver != null){
            driver.quit();
            driver = null; // set driver to null after quit
        }
    }
}
