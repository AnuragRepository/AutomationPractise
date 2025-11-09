package Assignments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment4 {

    @Test
    public void test() {

        //Print middle in output
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Nested Frames")).click();
        driver.switchTo().frame("frame-top");
        //WebElement frameTOPROW = driver.findElement(By.xpath("//*[@name='frame-top']"));
        //System.out.println("frameTOPROW Text = "+frameTOPROW.getText());

        driver.switchTo().frame("frame-left");
        WebElement frameLeft = driver.findElement(By.tagName("body"));
        System.out.println("frameLeft Text = "+frameLeft.getText());
        Assert.assertEquals(frameLeft.getText(),"LEFT");
        driver.switchTo().defaultContent();

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        WebElement frameMiddle = driver.findElement(By.xpath("//div[@id='content']"));
        System.out.println("frameMiddle Text = "+frameMiddle.getText());
        Assert.assertEquals(frameMiddle.getText(),"MIDDLE");
        driver.switchTo().defaultContent();

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-right");
        WebElement frameRight = driver.findElement(By.tagName("body"));
        System.out.println("frameRight Text = "+frameRight.getText());
        Assert.assertEquals(frameRight.getText(),"RIGHT");
        driver.switchTo().defaultContent();

        driver.switchTo().frame("frame-bottom");
        WebElement frameBottom = driver.findElement(By.tagName("body"));
        System.out.println("frameBottom Text = "+frameBottom.getText());
        Assert.assertEquals(frameBottom.getText(),"BOTTOM");
        driver.switchTo().defaultContent();
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
