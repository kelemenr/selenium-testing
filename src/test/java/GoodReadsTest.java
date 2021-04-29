package tests;

import org.junit.*;
import java.util.*; 
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class GoodReadsTest {
    public WebDriver driver;
    public WebDriverWait wait;

    public String testEmail = "seleniumtestassignment@gmail.com";
    public String testPassword = "SeleniumTest123";

    public Map<String, String> multiplePageTestData = new HashMap<String, String>(){{
        put("mainPage", "Deciding what to read next?");
        put("userPage", "CURRENTLY READING");
        put("searchResultPage", "Search");
        put("explorePage", "Explore Books");
        put("recommendationsPage", "Recommendations");
        put("storyPage", "Add My Writing");}};
    
    @Before
    public void setup() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("platform", "win10");

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);       
    }
    
    @Test
    public void testLogin() {
        MainPage mainPage = new MainPage(this.driver);
        UserPage userPage = mainPage.logIn(testEmail, testPassword);
        Assert.assertEquals("CURRENTLY READING", userPage.getUserPageText());
    }

    @Test
    public void sendFormWithUser() {
        MainPage mainPage = new MainPage(this.driver);
        UserPage userPage = mainPage.logIn(testEmail, testPassword);
        SearchResultPage searchResultPage = userPage.mainSearch();
        Assert.assertEquals("Search", searchResultPage.getSearchResultPageText());
    }

    @Test
    public void testLogOut() {
        MainPage mainPage = new MainPage(this.driver);
        UserPage userPage = mainPage.logIn(testEmail, testPassword);
        MainPage newMainPage = userPage.logOut();
        Assert.assertEquals("Deciding what to read next?", newMainPage.getMainPageText());
    }

    @Test
    public void fillInput() {
        MainPage mainPage = new MainPage(this.driver);
        UserPage userPage = mainPage.logIn(testEmail, testPassword);
        SearchResultPage searchResultPage = userPage.sideSearch();
        Assert.assertEquals("Search", searchResultPage.getSearchResultPageText());
    }

    @Test
    public void sendFormWithoutUser() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.search("Seneca");
    }

    @Test
    public void readTextArea() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.getMainPageText();
    }
    
    @Test
    public void multiplePageTestFromArray() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertEquals(multiplePageTestData.get("mainPage"), mainPage.getMainPageText());
        UserPage userPage = mainPage.logIn(testEmail, testPassword);
        Assert.assertEquals(multiplePageTestData.get("userPage"), userPage.getUserPageText());
        SearchResultPage searchResultPage = userPage.mainSearch();
        Assert.assertEquals(multiplePageTestData.get("searchResultPage"), searchResultPage.getSearchResultPageText());
        searchResultPage.pageBack();
        Assert.assertEquals(multiplePageTestData.get("userPage"), userPage.getUserPageText());
        ExplorePage explorePage = userPage.goToExplore();
        Assert.assertEquals(multiplePageTestData.get("explorePage"), explorePage.getExplorePageText());
        searchResultPage.pageBack();
        RecommendPage recommendPage = userPage.goToRecommendations();
        Assert.assertEquals(multiplePageTestData.get("recommendationsPage"), recommendPage.getRecommendPageText());
    }

    @Test
    public void fillDropDown() {
        MainPage mainPage = new MainPage(this.driver);
        UserPage userPage = mainPage.logIn(testEmail, testPassword);
        userPage.fillDropDown();
    }

    @Test 
    public void fillAndReadRadioButton() {
        MainPage mainPage = new MainPage(this.driver);
        UserPage userPage = mainPage.logIn(testEmail, testPassword);
        ExplorePage explorePage = userPage.goToExplore();
        explorePage.checkRadioButton();
    }

    @Test
    public void readPageTitle() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.readTitle();
    }

    @Test
    public void fileUpload() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.logIn(testEmail, testPassword);
        StoryPage storyPage = new StoryPage(this.driver);
        Assert.assertTrue(storyPage.uploadFile().contains("dog.jpg"));
    }

    @Test
    public void browserHistory() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.clickLink();
        mainPage.pageBack();
        Assert.assertEquals("Goodreads | Meet your next favorite book", driver.getTitle());
    }

    @Test
    public void addCookie() {
        MainPage mainPage = new MainPage(this.driver);
        UserPage userPage = mainPage.logIn(testEmail, testPassword);
        Cookie result = mainPage.addCookie("a_cookie");
        Assert.assertEquals("bar", result.getValue());
        mainPage.deleteCookie("a_cookie");
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
