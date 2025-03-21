package stepsDefinition;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductPage;
import pages.LoginPage;

public class LoginSteps{
    LoginPage loginPage;
    ProductPage productPage;

    @Given("SauceDemo login page is open")
    public void saucedemo_login_page_is_open() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        productPage = new ProductPage(DriverFactory.getDriver());
    }

    @And("login fields are empty")
    public void login_fields_are_empty() {
        Assert.assertTrue((loginPage.isUserNameEmpty() || loginPage.isPasswordEmpty()), "Login fields are empty");
    }

    @When("User click login button")
    public void user_click_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("User should see an error message {string}")
    public void user_should_see_an_error_message(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @When("User type {string} in username field")
    public void user_type_in_username_field(String username) {
        loginPage.inputUserName(username);
    }

    @And("User type {string} in password field")
    public void user_type_in_password_field(String password) {
        loginPage.inputPassword(password);
    }

    @Then("User successfully logs in and redirected to the {string} page")
    public void user_successfully_logs_in_and_redirected_to_the_product_page(String page) {
        String actualProductPage = productPage.productPageIsVisible();
        Assert.assertEquals(actualProductPage, page);
    }
}
