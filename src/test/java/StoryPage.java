package tests;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import org.openqa.selenium.support.ui.*;

public class StoryPage extends PageBase {

    private final By fileUploadLocator = By.cssSelector("input[type='file']");
     private final By fileUploadTextLocator = By.id("story_photo");

    public StoryPage(WebDriver driver){
        super(driver);
        this.driver.get("https://www.goodreads.com/story/new");
    }
   
    public String uploadFile(String fileName){
        WebElement addFile = this.waitAndReturnElement(fileUploadLocator);
        String path = System.getProperty("user.dir") + "\\images\\" + fileName;
        addFile.sendKeys(path);
        WebElement changedAddFile = this.waitAndReturnElement(fileUploadTextLocator);
        return changedAddFile.getAttribute("value");
    }
}
