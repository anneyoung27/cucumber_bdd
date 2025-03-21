package stepsDefinition;

import io.cucumber.java.en.Then;
import pages.ProductPage;
import factory.DriverFactory;

public class ProductSteps extends DriverFactory {
    ProductPage productsPage;

    @Then("User should see a list of available products")
    public void userShouldSeeListOfProducts() {
        productsPage = new ProductPage(DriverFactory.getDriver());
        productsPage.verifyIfProductsIsLoaded();
    }




}
