package tests;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.support.ui.*;

public class ExplorePage extends PageBase {

    private final By explorePageTextLocator = By.xpath("/html/body/div[2]/div[3]/div[1]/div[2]/h1");
    private final By radioButtonLocator = By.xpath("//div[@class='greyText fieldsToSearch']/input[3]");

    public ExplorePage(WebDriver driver){
        super(driver);
    }

    public String getExplorePageText(){    
        return this.waitAndReturnElement(explorePageTextLocator).getText();
    }

    public void checkRadioButton(){
        WebElement radioButton = this.waitAndReturnElement(radioButtonLocator);
        radioButton.click();
        Assert.assertEquals(true, radioButton.isSelected());
    }
}
