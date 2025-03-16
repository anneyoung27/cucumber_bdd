package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public class LoginPage extends DriverManager {
    WebDriver driver;

    public static Logger log = LogManager.getLogger();

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    By loginPageIsVisible = By.xpath("//div[@class='login_logo']");

    By userName = By.xpath("//input[@id='user-name']");

    By password = By.xpath("//input[@id='password']");

    By loginButton = By.xpath("//input[@id='login-button']");

    By productLabel = By.xpath("//div[@class='product_label']");

    public void loginPageIsVisible(){
        if(driver.findElement(loginPageIsVisible).isDisplayed()){
            log.info("{} element is visible", loginPageIsVisible.toString());
        }else{
            log.error("{} element has not found", loginPageIsVisible.toString());
        }
    }

    public void inputUserName(String user_name){
        driver.findElement(userName).sendKeys(user_name);
        log.info("Entered username: {} into the username field", userName); // Temporary logging for practice purposes. Not recommended for production.
    }

    public void inputPassword(String user_pass){
        driver.findElement(password).sendKeys(user_pass);
        log.info("Entered password: {} into the password field", password); // Temporary logging for practice purposes. Not recommended for production.
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
        log.info("Clicked login button: {}", loginButton.toString());
    }

    public String getProductLabel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));

        log.info("Waiting for product label to be visible: {}", productLabel.toString());
        WebElement labelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productLabel));

        String productText = labelElement.getText().trim();

        if (productText.isEmpty()) {
            log.warn("Product label is visible but text is empty!");
        } else {
            log.info("Retrieved product label: '{}'", productText);
        }

        return productText;
    }

}