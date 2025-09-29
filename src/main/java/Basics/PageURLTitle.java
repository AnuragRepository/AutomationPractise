package Basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class PageURLTitle {

    public static void main (String[] args)
    {
        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://selectorshub.com/xpath-practice-page/");

        System.out.println("The title of webpage = "+driver.getTitle());
        System.out.println("Landed URL = "+driver.getCurrentUrl());
        System.out.println("Landed URL = "+driver.getPageSource());
        driver.close();

    }






}
