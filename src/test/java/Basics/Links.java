package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Links {

   static WebDriver driver;

    Links()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void test() {

       /* 1. total no. of links in page
        2. total no. of links in footersection of page
        3. total no. of links in 1st column out of 5 columns in footersection of page
        4. Click on each 1st column link and check if pages (windows) are open or not*/

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement footerSection = Links.driver.findElement(By.id("gf-BIG"));
        WebElement  footerSectionFirstColumnLinks = footerSection.findElement(By.xpath("//table[@class='gf-t']/tbody/tr/td[1]/ul"));

        countLinkEntirePage();
        countLinkFooter(footerSection);
        countLinkFooterFirstColumn(footerSectionFirstColumnLinks);
        validateLinkFooterFirstColumn(footerSectionFirstColumnLinks);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

    public static void countLinkEntirePage()
    {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of Links in Page = "+links.size());
        Assert.assertEquals(links.size(),27);
    }

    public static void countLinkFooter(WebElement footerSection)
    {

        List<WebElement> linksFooter = footerSection.findElements(By.tagName("a"));
        System.out.println("Number of Links in footerSection = "+linksFooter.size());
        Assert.assertEquals(linksFooter.size(),20);
    }

    public static void countLinkFooterFirstColumn(WebElement footerSectionFirstColumnLinks)
    {
        List<WebElement> footerSectionFirstColumnLinksCount= footerSectionFirstColumnLinks.findElements(By.tagName("a"));
        System.out.println("Number of Links in footerSectionFirstColumn = "+footerSectionFirstColumnLinksCount.size());
       Assert.assertEquals(footerSectionFirstColumnLinksCount.size(),5);
    }
    public static void validateLinkFooterFirstColumn(WebElement footerSectionFirstColumnLinks)
    {
        for(int i=1 ;i<footerSectionFirstColumnLinks.findElements(By.tagName("a")).size();i++)
        {
           footerSectionFirstColumnLinks.findElements(By.tagName("a")).get(i).sendKeys(Keys.CONTROL,Keys.ENTER);
        }
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        while(it.hasNext())
        {
            driver.switchTo().window(it.next());
            System.out.println(driver.getTitle());
            if(driver.getTitle().equalsIgnoreCase("Practice Page")||driver.getTitle().equalsIgnoreCase("The World’s Most Popular API Testing Tool | SoapUI")||driver.getTitle().equalsIgnoreCase("Appium Mobile Automation Testing from Scratch + Frameworks Tutorial | Udemy")||driver.getTitle().equalsIgnoreCase("Apache JMeter - Apache JMeter™")||driver.getTitle().equalsIgnoreCase("Learn REST API Design - REST API Tutorial"))
            {
                Assert.assertTrue(true);
            }
            else {
                Assert.assertTrue(false);
            }

        }
    }




}
