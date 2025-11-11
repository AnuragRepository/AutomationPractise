package Cookie;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class cookiePrac {
    @Test
    public void test()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.manage().deleteAllCookies();
        //driver.manage().deleteCookieNamed("");
        //driver.manage().deleteCookie(Cookie );
       // driver.manage().addCookie(Cookie);

        //WAP to app  logout after deleting session cooky
        driver.manage().deleteCookieNamed("sessionKey");
        //driver.findElement(By.linkText("anyLinkOnWebPage")).click();
        System.out.println(driver.getCurrentUrl());
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
