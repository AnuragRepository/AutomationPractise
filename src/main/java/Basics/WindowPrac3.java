package Basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class WindowPrac3 {

    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Multiple Windows")).click();
        driver.findElement(By.linkText("Click Here")).click();
        Set<String> windowsID= driver.getWindowHandles();
        Iterator<String> it = windowsID.iterator();
        String parentID= it.next();
        String childID= it.next();
        driver.switchTo().window(childID);
        String childwindowText= driver.findElement(By.xpath("(//*[text()='New Window'])[2]")).getText();
        System.out.println(childwindowText);
        driver.switchTo().window(parentID);
        String parentwindowText=driver.findElement(By.xpath("//*[text()='Opening a new window']")).getText();
        System.out.println(parentwindowText);
    }

}
