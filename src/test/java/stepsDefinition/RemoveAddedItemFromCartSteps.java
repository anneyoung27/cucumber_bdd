package stepsDefinition;

import io.cucumber.java.en.And;
import pages.HomePage;
import utils.DriverManager;

public class RemoveAddedItemFromCartSteps extends DriverManager {
    HomePage homePage = new HomePage(driver);

    @And("User click remove button that added product in the cart")
    public void user_click_remove_button_that_added_product_in_the_cart() {
        homePage.clickRemoveButton();
    }
}
