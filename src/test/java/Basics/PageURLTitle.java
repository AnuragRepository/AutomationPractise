package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class PageURLTitle {

    @Test
     public void test()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://selectorshub.com/xpath-practice-page/");

        System.out.println("The title of webpage = "+driver.getTitle());
        System.out.println("Landed URL = "+driver.getCurrentUrl());
        System.out.println("Landed URL = "+driver.getPageSource());
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }






}
