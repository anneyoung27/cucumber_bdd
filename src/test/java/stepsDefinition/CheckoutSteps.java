package stepsDefinition;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckoutPage;

public class CheckoutSteps {
    static CheckoutPage checkoutPage;

    static {
        checkoutPage = new CheckoutPage(DriverFactory.getDriver());
    }

    @When("User clicks checkout button")
    public void userClicksCheckoutButton() {
        checkoutPage.clickCheckoutButton();
    }

    @And("User fills in {string} , {string}, {string} data")
    public void userFillHisData(String first_name, String last_name, String zip_code) {
        checkoutPage.fillUserInformation(first_name, last_name, zip_code);
    }

    @And("User clicks the continue button to proceed to checkout")
    public void userClicksTheContinueButtonToProceedToCheckout() {
        checkoutPage.clickContinueToCheckoutButton();
    }

    @Then("User should see Checkout Overview page")
    public void userShouldSeeCheckoutOverviewPage() {
        String actualCheckoutOverviewLabel = checkoutPage.verifyIfCheckoutOverviewPageIsVisible();
        Assert.assertEquals(actualCheckoutOverviewLabel, "Checkout: Overview");
    }


    @And("User verifies that the order total amount are correct")
    public void userVerifiesThatTheOrderDetailsAndTotalAmountAreCorrect() {
        double actualOrderedTotal = checkoutPage.getActualTotalValue();
        Assert.assertEquals((checkoutPage.getSubTotalValue() + checkoutPage.getTaxValue()), actualOrderedTotal);
    }

    @And("User clicks the finish button to complete the checkout")
    public void userClicksTheFinishButtonToCompleteTheCheckout() {
        checkoutPage.clickFinishButton();
    }

    @Then("User should see a confirmation that the checkout was successful")
    public void userShouldSeeAConfirmationThatTheCheckoutWasSuccessful() {
        String actualSuccessOrdered = checkoutPage.successConfirmationOrdered();
        Assert.assertEquals(actualSuccessOrdered, "THANK YOU FOR YOUR ORDER");
    }
}
