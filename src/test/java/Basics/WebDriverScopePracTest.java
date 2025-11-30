package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WebDriverScopePracTest {

    @Test
    public void test() {
    /*1. WAP to find total no. of links in webpage
    2.Get links count of only in footer section
    3. Get links count of specific column to footer section
    4. Click on each link of specific column of footer section and check if page opens or not/ links working or not
    */
      WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.get("https://rahulshettyacademy.com/AutomationPractice/");
      WebElement footerDriver =  driver.findElement(By.xpath("//div[@id='gf-BIG']"));
      //WebElement  footerDriverSecondColumn = footerDriver.findElement(By.xpath(".//table/tbody/tr/td[2]"));
      WebElement  footerDriverSecondColumn = footerDriver.findElement(By.xpath("//table/tbody/tr/td[2]/ul"));
      totalNumberOfLinks(driver);
      totalNumberOfFooterLinks(footerDriver);
      totalNumberOfSecondColumnFooterLinks(footerDriverSecondColumn);
      validateNumberOfSecondColumnFooterLinks(footerDriverSecondColumn,driver);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

    public static void validateNumberOfSecondColumnFooterLinks(WebElement footerDriverSecondColumn,WebDriver driver)
    {
        for(int i=1;i<footerDriverSecondColumn.findElements(By.tagName("a")).size();i++)
        {
            footerDriverSecondColumn.findElements(By.tagName("a")).get(i).sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
        }
        Set<String> windowHandles= driver.getWindowHandles();
        Iterator<String> it =  windowHandles.iterator();
        while(it.hasNext())
        {
          System.out.println(driver.switchTo().window(it.next()).getTitle());
        }
    }

    public static void totalNumberOfSecondColumnFooterLinks(WebElement footerDriverSecondColumn)
    {
      List<WebElement> totalSecondColumnFooterLinks =  footerDriverSecondColumn.findElements(By.tagName("a"));
      System.out.println("Number of totalNumberOfSecondColumnFooterLinks = "+totalSecondColumnFooterLinks.size());
    }

    public static void totalNumberOfFooterLinks(WebElement footerDriver)
    {
       List<WebElement> footerLinks = footerDriver.findElements(By.tagName("a"));
       System.out.println("Number of footerlinks = "+footerLinks.size());
    }

    public static void totalNumberOfLinks(WebDriver driver)
    {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of links = "+links.size());
    }

}


