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

    By first_name = By.xpath("(//input[@class='form_input'])[1]");

    By last_name = By.xpath("//input[@data-test='lastName']");

    By zip_code = By.xpath("//input[@data-test='postalCode']");

    By continueToProceedCheckoutButton = By.xpath("//input[@class='btn_primary cart_button']");

    By checkoutOverviewLabel = By.xpath("//div[normalize-space(text())='Checkout: Overview']");

    By checkoutFinishButton = By.xpath("//a[@class='btn_action cart_button']");

    By successMessageLabel = By.xpath("//h2[normalize-space(text())='THANK YOU FOR YOUR ORDER']");

    By subTotalElement = By.className("summary_subtotal_label");

    By taxElement = By.className("summary_tax_label");

    By totalValueElement = By.className("summary_total_label");

    public void clickCheckoutButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

        checkoutBtn.click();

        log.info("Checkout button clicked successfully: {}", checkoutButton.toString());
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

    public void clickContinueToCheckoutButton(){
        log.info("Clicking the 'Continue to Checkout' button");
        driver.findElement(continueToProceedCheckoutButton).click();
        log.info("'Continue to Checkout' button clicked");
    }

    public String verifyIfCheckoutOverviewPageIsVisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        WebElement checkoutHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutOverviewLabel));

        String headerText = checkoutHeader.getText();
        log.info("Checkout Overview page is visible with header: '{}'", headerText);
        return headerText;
    }

    public void clickFinishButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(checkoutFinishButton));

        log.info("Clicking the 'Finish' button");
        finishButton.click();
        log.info("'Finish' button clicked");
    }

    public String successConfirmationOrdered(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        WebElement confirmationMessageLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLabel));

        String confirmationText = confirmationMessageLabel.getText();
        log.info("Order success message displayed: '{}'", confirmationText);
        return confirmationText;
    }

    public double getTaxValue(){
        String taxText = driver.findElement(taxElement).getText();
        String taxValue = taxText.replace("Tax: $", "").trim();

        return Double.parseDouble(taxValue);
    }

    public double getSubTotalValue(){
        String subTotal = driver.findElement(subTotalElement).getText();
        String subTotalValue = subTotal.replace("Item total: $", "").trim();

        return Double.parseDouble(subTotalValue);
    }

    public double getActualTotalValue(){
        String total = driver.findElement(totalValueElement).getText();
        String totalValue = total.replace("Total: $", "").trim();

        return Double.parseDouble(totalValue);
    }


}
