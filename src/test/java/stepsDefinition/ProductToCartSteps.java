package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.HomePage;
import utils.DriverManager;

public class ProductToCartSteps extends DriverManager {
    HomePage homePage = new HomePage(driver);

    @Then("User verify that the product page is visible")
    public void userVerifyThatTheProductPageIsVisible() {
        homePage.verifyIfProductsIsLoaded();
    }

    @And("User click add to cart button on the first product")
    public void userClickAddToCartButtonOnTheFirstProduct() {
        homePage.clickAddProduct();
    }

    @And("User click on the shopping cart icon in the top right corner")
    public void userClickOnTheShoppingCartIconInTheTopRightCorner() {
        homePage.clickCartButton();
    }

    @And("User verify that added product is exists in the cart")
    public void userVerifyThatAddedProductIsExistsInTheCart() {
        homePage.verifyIfAddedProductExistInCart();
    }
}
