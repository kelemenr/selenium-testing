package tests;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.support.ui.*;

public class SearchResultPage extends PageBase{

    private final By searchTextLocator = By.xpath("//div[@class='leftContainer']/h1");

    public SearchResultPage(WebDriver driver){
        super(driver);
    }

    public String getSearchResultPageText(){
        WebElement searchText = waitAndReturnElement(searchTextLocator);
        return searchText.getText();
    }
}
