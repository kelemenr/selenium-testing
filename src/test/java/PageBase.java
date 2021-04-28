package tests;

import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;	

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
        
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
    
    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    } 

    public void pageBack(){
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("window.history.go(-1)");
    }

    public Cookie addCookie(String name) {
        this.driver.manage().addCookie(new Cookie(name, "bar"));
        Cookie cookie = this.driver.manage().getCookieNamed(name);
        // System.out.println(cookie);
        return cookie;
    }

    public void deleteCookie(String name) {
        Cookie cookie = this.driver.manage().getCookieNamed(name);
        this.driver.manage().deleteCookie(cookie);
    }
   
}
