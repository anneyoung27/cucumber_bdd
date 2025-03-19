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
import java.util.Optional;

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

    By errorMessage = By.xpath("//h3[@data-test='error']");

    public void loginPageIsVisible(){
        if(driver.findElement(loginPageIsVisible).isDisplayed()){
            log.info("Opening SauceDemo login page..");
            log.info("{} element is visible", loginPageIsVisible.toString());
        }else{
            log.warn("{} element has not found", loginPageIsVisible.toString());
        }
    }

    public boolean isUserNameEmpty(){
        WebElement user_name = driver.findElement(userName);
        // get value of the input field
        String userNameValue = Optional.ofNullable(user_name.getDomAttribute("value")).orElse("");

        if (userNameValue.isEmpty()){
            log.warn("Username field is empty");
            return true;
        }else {
            log.info("Username field is not empty");
            return false;
        }
    }

    public boolean isPasswordEmpty(){
        WebElement pass_word = driver.findElement(password);
        // get value of the input field
        String passwordValue = Optional.ofNullable(pass_word.getDomAttribute("value")).orElse("");

        if (passwordValue.isEmpty()){
            log.warn("Password field is empty");
            return true;
        }else {
            log.info("Password field is not empty");
            return false;
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

    public String getErrorMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));

        log.info("Waiting for error message label to be visible: {}", errorMessage.toString());
        WebElement labelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

        String errorMessageText = labelElement.getText().trim();

        if (errorMessageText.isEmpty()) {
            log.warn("Error message label is visible but text is empty!");
        } else {
            log.info("Retrieved error message label: '{}'", errorMessageText);
        }

        return errorMessageText;
    }

}