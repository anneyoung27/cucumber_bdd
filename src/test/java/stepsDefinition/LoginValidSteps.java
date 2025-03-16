package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverManager;

public class LoginValidSteps extends DriverManager {
    LoginPage loginPage;
    HomePage homePage;

    @Given("User navigates to the sauce demo website")
    public void user_navigates_to_the_sauce_demo_website() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @When("User verify that the login page is visible")
    public void user_verify_that_the_login_page_is_visible() {
        loginPage.loginPageIsVisible();
    }

    @And("User enters username {string}")
    public void user_enters_username(String username) {
        loginPage.inputUserName(username);
    }

    @And("User enters password {string}")
    public void user_enters_password(String password) {
        loginPage.inputPassword(password);
    }

    @Then("User click login button")
    public void user_click_login_button() {
        loginPage.clickLoginButton();
    }

    @And("User should see {string} page")
    public void user_should_see_product_page(String page) {
        String actualPage = loginPage.getProductLabel();
        Assert.assertEquals(actualPage, page);
    }
}
