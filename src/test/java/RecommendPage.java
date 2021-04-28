package tests;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.support.ui.*;

public class RecommendPage extends PageBase {

    private final By recommendPageTextLocator = By.xpath("/html/body/div[2]/div[3]/div[1]/div[2]/h1");

    public RecommendPage(WebDriver driver){
        super(driver);
    }

    public String getRecommendPageText(){    
        return this.waitAndReturnElement(recommendPageTextLocator).getText();
    }
}
