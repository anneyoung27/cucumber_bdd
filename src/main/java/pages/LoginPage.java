package pages;

import utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends DriverManager {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By loginPageIsVisible = By.xpath("//div[@class='login_logo']");

    By userName = By.xpath("//input[@id='user-name']");

    By password = By.xpath("//input[@id='password']");

    By loginButton = By.xpath("//input[@id='login-button']");

    public void loginPageIsVisible(){
        driver.findElement(loginPageIsVisible).isDisplayed();
    }

    public void inputUserName(String user_name){
        driver.findElement(userName).sendKeys(user_name);
    }

    public void inputPassword(String user_pass){
        driver.findElement(password).sendKeys(user_pass);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
}
