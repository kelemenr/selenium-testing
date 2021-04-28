package tests;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;

import org.openqa.selenium.support.ui.*;

public class MainPage extends PageBase{

    private final By emailLocator = By.id("userSignInFormEmail");
    private final By passwordLocator = By.id("user_password");
    private final By signInButtonLocator = By.cssSelector("input[type='submit']");
    private final By searchFieldLocator = By.id("sitesearch_field");
    private final By searchButtonLocator = By.cssSelector("[alt=search]");
    private final By textAreaLocator = By.xpath("//div[@class='sellingPointBoxLeft']/h2");
    private final By linkTextLocator = By.linkText("Best Books 2020");

    public MainPage(WebDriver driver){
        super(driver);
        this.driver.get("https://www.goodreads.com/");
    }

    public UserPage logIn(String email, String password){
        WebElement emailField = this.waitAndReturnElement(emailLocator);
        String userEmail = email;
        emailField.sendKeys(userEmail);

        WebElement passwordField = waitAndReturnElement(passwordLocator);
        String userPassword = password;
        passwordField.sendKeys(userPassword);

        WebElement submitButton = waitAndReturnElement(signInButtonLocator);
        submitButton.click();
        return new UserPage(this.driver);
    }

    public void search(String input){
        WebElement searchField = waitAndReturnElement(searchFieldLocator);
        searchField.click();

        String userInput = input;
        searchField.sendKeys(userInput);

        WebElement searchButton = waitAndReturnElement(searchButtonLocator);
        searchButton.click();
    }

    public String getMainPageText(){
        WebElement text = waitAndReturnElement(textAreaLocator);
        //Assert.assertEquals("Deciding what to read next?", value);
        return text.getText();
    }

    public void readTitle(){
        String pageTitle = driver.getTitle();
        System.out.println("Page title is : " + driver.getTitle());
    }

    public void clickLink(){
        WebElement text = waitAndReturnElement(linkTextLocator);
        text.click();
    }
}
