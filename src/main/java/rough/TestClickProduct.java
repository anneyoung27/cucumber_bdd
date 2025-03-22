package rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestClickProduct {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/");


        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_item_name")));

        List<WebElement> productListName = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

        boolean productFound = false;

        for (WebElement product : productListName) {
            System.out.println("Checking product: " + product.getText().trim());

            if (product.getText().trim().equalsIgnoreCase("Sauce Labs Bike Light")) {
                productFound = true;

                WebElement parentElement = product.findElement(By.xpath("./ancestor::div[contains(@class,'inventory_item')]"));

                WebElement addToCartElement = wait.until(ExpectedConditions.elementToBeClickable(
                        parentElement.findElement(By.xpath(".//button"))
                ));

                System.out.println("Product found! Clicking 'Add to Cart' button...");
                addToCartElement.click();
                System.out.println("Product Sauce Labs Bike Light added to cart.");
                break;
            }
        }

        if (!productFound) {
            System.out.println("Product Sauce Labs Bike Light not found!");
        }

        driver.quit();
    }
}
