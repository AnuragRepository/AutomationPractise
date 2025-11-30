package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Alert_WindowAuthenticationTest {

    @Test
    public void windowAuthenticatioPopUP()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

     /* Pass username and password in URL
        Syntax = http://username:password@urlexcudinghttps://
        https://admin:admin@the-internet.herokuapp.com/
       */

        String username = "admin";
        String password = "admin";
        driver.get("https://"+username+":"+password+"@the-internet.herokuapp.com/");


        WebElement basicAuthHyperLinkWith_A_tag = driver.findElement(By.linkText("Basic Auth"));
        basicAuthHyperLinkWith_A_tag.click();
        WebElement header = driver.findElement(By.xpath("//div[@class='example']"));
        System.out.println(header.getText());
        Assert.assertEquals(header.getText(),"Basic Auth\n" +
                "Congratulations! You must have the proper credentials.");
        driver.quit();
    }


}
