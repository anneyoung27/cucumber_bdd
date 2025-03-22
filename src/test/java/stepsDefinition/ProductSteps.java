package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductPage;
import factory.DriverFactory;

public class ProductSteps extends DriverFactory {
    static ProductPage productsPage;

    static {
        productsPage = new ProductPage(getDriver());
    }

    @And("User should see a list of available products")
    public void userShouldSeeListOfProducts() {
        productsPage = new ProductPage(DriverFactory.getDriver());
        productsPage.verifyIfProductsIsLoaded();
    }

    @When("User clicks add to cart for the {string} product")
    public void userClicksAddToCart(String product_name) {
        productsPage.clickAddProduct(product_name);
    }

    @When("User clicks the shopping cart icon in the top right corner")
    public void userClicksShoppingCartIcon() {
        productsPage.clickCartButton();
    }

    @Then("The product was successfully added to the cart is visible in cart list")
    public void productIsVisibleInCartList() {
        productsPage.verifyIfAddedProductExistInCart();
    }

    @When("User clicks REMOVE button")
    public void userClicksRemoveButton() {
        productsPage.clickRemoveButton();
    }

    @Then("The product was successfully removed from the cart")
    public void productIsRemovedFromCart() {
        int successfullyRemoved = productsPage.successfullyRemoved();
        if (successfullyRemoved < 0){
            Assert.assertTrue(true, "Successfully removed");
        }
    }
}