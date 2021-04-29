package tests;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.*;

public class UserPage extends PageBase {

    private final By dropDownLocator = By.xpath("//div[@class='primaryNavMenu primaryNavMenu--siteHeaderBrowseMenu ignore-react-onclickoutside']/a/span");
    private final By dropDownRecommendLocator = By.xpath("//div[@class='primaryNavMenu__menu primaryNavMenu__menu--show gr-box gr-box--withShadowLarge']/div/ul/li/a[1]");    
    private final By dropDownExploreLocator = By.xpath("//div[@class='siteHeader__browseMenuDropdown']/ul/li[7]/a");
    private final By radioButtonLocator = By.xpath("//div[@class='greyText fieldsToSearch']/input[3]");
    private final By userPageTextLocator = By.xpath("//section[@class='currentlyReadingShelf']/h3");
    private final By profileLogoLocator = By.xpath("/html/body/div[2]/div/header/div[1]/div/div[3]/ul/li[5]/div/a/span/img");
    private final By logOutLocator = By.xpath("/html/body/div[2]/div/header/div[1]/div/div[3]/ul/li[5]/div/div/div/ul/li[13]/a");
    private final By searchBarLocator = By.cssSelector(".searchBox__input.searchBox__input--navbar");
    private final By searchButtonLocator = By.cssSelector("[aria-label=Search]");
    private final By sideSearchBarLocator = By.cssSelector(".searchBox__input.searchBox__input--currentlyReading");
    private final By sideSearchButtonLocator = By.cssSelector(".searchBox__icon--magnifyingGlass.gr-iconButton.searchBox__icon.searchBox__icon--currentlyReading");

    public UserPage(WebDriver driver){
        super(driver);
        this.driver.get("https://www.goodreads.com/");
    }

    public MainPage logOut(){
        WebElement profileLogo = waitAndReturnElement(profileLogoLocator);
        profileLogo.click();

        WebElement signOut = waitAndReturnElement(logOutLocator);
        signOut.click();
        return new MainPage(this.driver);
    }

    public void fillDropDown(){
        WebElement dropDown = this.waitAndReturnElement(dropDownLocator);
        dropDown.click();
        WebElement dropDownValue = this.waitAndReturnElement(dropDownRecommendLocator);
        dropDownValue.click();
    }

    public ExplorePage goToExplore(){
        WebElement dropDown = this.waitAndReturnElement(dropDownLocator);
        dropDown.click();
        
        WebElement dropDownValue = this.waitAndReturnElement(dropDownExploreLocator);
        dropDownValue.click();
        return new ExplorePage(this.driver);
    }

    public RecommendPage goToRecommendations(){
        WebElement dropDown = this.waitAndReturnElement(dropDownLocator);
        dropDown.click();

        WebElement dropDownValue = this.waitAndReturnElement(dropDownRecommendLocator);
        dropDownValue.click();
        return new RecommendPage(this.driver);
    }

    public String getUserPageText(){    
        return this.waitAndReturnElement(userPageTextLocator).getText();
    }

    public SearchResultPage mainSearch(String input){  
        WebElement searchBar = waitAndReturnElement(searchBarLocator);
        searchBar.click();

        String searchValue = input;
        searchBar.sendKeys(searchValue);

        WebElement searchButton = waitAndReturnElement(searchButtonLocator);
        searchButton.click();  
        return new SearchResultPage(this.driver);
    }

    public SearchResultPage sideSearch(String input){  
        WebElement searchField = waitAndReturnElement(sideSearchBarLocator);
        searchField.click();

        String book = input;
        searchField.sendKeys(book);

        WebElement searchButton = waitAndReturnElement(sideSearchButtonLocator);
        searchButton.click();
        return new SearchResultPage(this.driver);
    }
}
