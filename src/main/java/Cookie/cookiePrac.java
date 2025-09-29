package Cookie;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class cookiePrac {
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().deleteCookieNamed("");
        //driver.manage().deleteCookie(Cookie );
       // driver.manage().addCookie(Cookie);

        //WAP to app  logout after deleting session cooky
        driver.manage().deleteCookieNamed("sessionKey");
        driver.findElement(By.linkText("anyLinkOnWebPage")).click();
        System.out.println(driver.getCurrentUrl());
    }

}
