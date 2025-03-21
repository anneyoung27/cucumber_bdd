package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import factory.DriverFactory;

import java.time.Duration;

public class CheckoutPage extends DriverFactory {
    WebDriver driver;

    public static Logger log = LogManager.getLogger();

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    By checkoutButton = By.xpath("//a[@class='btn_action checkout_button']");

    By checkoutPageLabel = By.xpath("//div[@class='header_container']/following-sibling::div[1]");

    By first_name = By.xpath("(//input[@class='form_input'])[1]");

    By last_name = By.xpath("//input[@data-test='lastName']");

    By zip_code = By.xpath("//input[@data-test='postalCode']");

    By continueToProceedCheckoutButton = By.xpath("//input[@class='btn_primary cart_button']");

    By checkoutOverviewLabel = By.xpath("//div[normalize-space(text())='Checkout: Overview']");

    By checkoutFinishButton = By.xpath("//a[@class='btn_action cart_button']");

    public void clickCheckoutButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutBtn.click();
        log.info("Checkout button clicked successfully: {}", checkoutButton.toString());
    }

    public String verifyIfCheckoutPageIsVisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        WebElement checkoutHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPageLabel));
        log.info("Retrieved product label in checkout information: '{}'", checkoutPageLabel.toString());
        return checkoutHeader.getText();
    }

    public void fillUserInformation(String firstName, String lastName, String zipCode){
        try {
            driver.findElement(first_name).sendKeys(firstName);
            log.info("First Name entered: {}", firstName);

            driver.findElement(last_name).sendKeys(lastName);
            log.info("Last Name entered: {}", lastName);

            driver.findElement(zip_code).sendKeys(zipCode);
            log.info("Zip Code entered: {}", zipCode);

            log.info("✔️ User information form filled successfully.");
        } catch (Exception e) {
            log.error("Failed to fill user information!", e);
            throw e;
        }
    }

    public void clickContinueButton(){
        driver.findElement(continueToProceedCheckoutButton).click();
    }

    public void verifyIfCheckoutOverviewPageIsVisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        WebElement checkoutHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutOverviewLabel));
        log.info("Retrieved product label in checkout overview: '{}'", checkoutOverviewLabel.toString());
        checkoutHeader.getText();
    }

    public void clickFinishButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutFinishButton));
        checkoutBtn.click();
        log.info("Checkout finish button clicked successfully: {}", checkoutFinishButton.toString());
    }


}
