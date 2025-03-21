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
import java.util.List;

public class ProductPage extends DriverFactory {
    WebDriver driver;
    public static Logger log = LogManager.getLogger();

    // 1. Constructor of the page class
    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    // 2. By locators
    By productPageLabel = By.xpath("//div[@class='product_label']");
    By allProductsIsVisible = By.xpath("//div[@class='inventory_item']");
    By addProduct1 = By.xpath("//div[@class='inventory_list']//div[1]//div[3]//button[1]");
    By cartButton = By.xpath("//a[contains(@class,'shopping_cart_link fa-layers')]");
    By itemsInCart = By.cssSelector(".cart_item");
    By continueShoppingButton = By.xpath("//a[normalize-space()='Continue Shopping']");
    By addProduct2 = By.xpath("//body[@class='main-body']/div[@id='page_wrapper']/div[@id='contents_wrapper']/div[@id='inventory_container']/div/div[@id='inventory_container']/div[@class='inventory_list']/div[2]/div[3]/button[1]");
    By removeButton = By.xpath("//div[text()='29.99']/following-sibling::button");

    // 3. Page actions: features(behavior) of the page the form of methods
    public void verifyIfProductsIsLoaded(){
        List<WebElement> products = driver.findElements(allProductsIsVisible);

        if (products.isEmpty()){
            log.error("Products not found on Product page");
            return;
        }

        for (WebElement product : products){
            if (!product.isDisplayed()){
                log.info("One of the products is not visible on Product page");
            }
        }
        log.info("All products are successfully loaded and visible");
    }

    public void clickAddProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));
        WebElement labelElement = wait.until(ExpectedConditions.elementToBeClickable(addProduct1));

        labelElement.click();
        log.info("User clicked 'Add Product' button on Product Page: {}", addProduct1.toString());
    }

    public void clickCartButton(){
        driver.findElement(cartButton).click();
        log.info("User clicked 'Cart' button, navigating to Cart Page: {}", cartButton.toString());
    }

    public void verifyIfAddedProductExistInCart() {
        List<WebElement> cart = driver.findElements(itemsInCart);

        if (cart.isEmpty()) {
            log.error("Cart is empty! No products found!");
            return;
        }

        boolean allVisible = true;
        for (WebElement item : cart) {
            if (!item.isDisplayed()) {
                log.error("Product not added on Cart page");
                allVisible = false;
            }
        }

        if (allVisible) {
            log.info("Added product are visible");
        }
    }

    public void clickContinueShoppingButton(){
        driver.findElement(continueShoppingButton).click();
        log.info("User clicked 'Continue Shopping' button, navigating to Product Page: {}", continueShoppingButton.toString());
    }

    public void clickAddProduct2(){
        driver.findElement(addProduct2).click();
        log.info("'Add Product' button clicked successfully: {}", addProduct2.toString());
    }

    public void clickRemoveButton(){
        driver.findElement(removeButton).click();
        log.info("'REMOVE' button clicked successfully: {}", removeButton.toString());
    }

    public String productPageIsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(setUp.getProperty("EXPLICIT_WAIT"))));

        log.info("Waiting for product page label to be visible: {}", productPageLabel.toString());

        WebElement labelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productPageLabel));

        String labelText = labelElement.getText().trim();
        if (labelText.isEmpty()) {
            log.warn("Product page label is visible but text is empty!");
        } else {
            log.info("Retrieved product page label: '{}'", labelText);
        }
        return labelText;
    }
}
