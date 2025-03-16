package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.CheckoutPage;
import utils.DriverManager;

public class CheckoutTheProductSteps extends DriverManager {
    CheckoutPage checkoutPage = new CheckoutPage(driver);

    @Then("User click checkout button")
    public void user_click_checkout_button() {
        checkoutPage.clickCheckoutButton();
    }

    @And("User verify that the checkout page is visible")
    public void user_verify_that_the_checkout_page_is_visible() {
        String actualCheckoutPage = checkoutPage.verifyIfCheckoutPageIsVisible();
        Assert.assertEquals(actualCheckoutPage, "Checkout: Your Information");
    }

    @Then("User write his information {string}, {string}, {string}")
    public void user_write_his_information_firstname_lastname_zipcode(String firstName, String lastName, String zipCode) {
        checkoutPage.fillUserInformation(firstName, lastName, zipCode);
    }

    @And("User click continue button to proceed the checkout")
    public void user_click_continue_button_to_proceed_the_checkout() {
        checkoutPage.clickContinueButton();
    }

}
