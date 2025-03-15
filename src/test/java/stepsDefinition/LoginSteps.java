package stepsDefinition;


import pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static utils.DriverManager.*;

public class LoginSteps {
    LoginPage loginPage;

    @Given("User navigates to the saucedemo website")
    public void user_navigates_to_the_saucedemo_website() {
        setUp();
        loginPage = new LoginPage(driver);
    }

    @When("User verify that the login page is visible")
    public void user_verify_that_the_login_page_is_visible() {
        loginPage.loginPageIsVisible();
    }

    @And("User enters with {string} valid username")
    public void user_enters_with_valid_username(String userName) {
        loginPage.inputUserName(userName);
    }

    @And("User enters with {string} valid password")
    public void user_enters_with_valid_password(String password) {
        loginPage.inputPassword(password);
    }

    @Then("User click login button")
    public void user_click_login_button() {
        loginPage.clickLoginButton();
    }

    @And("User close the browser")
    public void user_close_the_browser() {
        tearDown();
    }
}