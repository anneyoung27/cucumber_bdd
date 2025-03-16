package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.HomePage;
import utils.DriverManager;

public class AddMoreProductSteps extends DriverManager {
    HomePage homePage = new HomePage(driver);

    @Then("User click continue shopping button to add more product")
    public void user_click_continue_shopping_button_to_add_more_product() {
        homePage.clickContinueShoppingButton();
    }

    @And("User click second product to cart")
    public void user_click_second_product_to_cart() {
        homePage.clickAddProduct2();
    }

    @And("User click shopping cart")
    public void user_click_shopping_cart() {
        homePage.clickCartButton();
    }
}
