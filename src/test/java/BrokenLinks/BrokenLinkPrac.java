package BrokenLinks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class BrokenLinkPrac {
    @Test
    public void test() throws IOException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        SoftAssert softassert = new SoftAssert();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        //driver.get("https://www.accenture.com/in-en");
        //List<WebElement> links =  driver.findElements(By.tagName("a")); // java.net.MalformedURLException due to # URL

        try {
            List<WebElement> links = driver.findElements(By.xpath("//table/tbody/tr/td[1]/ul/li/a")); //error not showing
            //List<WebElement> links =  driver.findElements(By.xpath("//li[@class='gf-li']/a")); // java.net.MalformedURLException due to # URL, but for rahul shetty not this exception
            for (WebElement link : links) {

                //System.out.println(link.getDomAttribute("href"));
                HttpURLConnection conn = (HttpURLConnection) new URL(link.getDomAttribute("href")).openConnection();
                conn.setRequestMethod("HEAD");
                conn.connect();
                System.out.println(conn.getResponseCode());
                softassert.assertTrue(conn.getResponseCode() < 400, "Link" + link.getText() + " failed with status " + conn.getResponseCode());
            }
            softassert.assertAll();
            closeBrowser(driver);
        } catch (AssertionError e) {
            e.printStackTrace();
           // throw new RuntimeException(e);
        }
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
