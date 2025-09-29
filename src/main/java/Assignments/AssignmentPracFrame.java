package Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AssignmentPracFrame {
     public static void main(String[] args)
     {
         System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
         WebDriver driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.get("https://the-internet.herokuapp.com/");
         driver.findElement(By.linkText("Nested Frames")).click();
         int totalFrames =  driver.findElements(By.tagName("frame")).size();
         System.out.println("totalFrames = "+totalFrames);
         driver.switchTo().frame("frame-top");
         int totalFramesUpper =  driver.findElements(By.tagName("frame")).size();
         System.out.println("totalFramesUpper = "+totalFramesUpper);
         driver.switchTo().frame("frame-middle");
         String middleText = driver.findElement(By.xpath("//div[@id='content']")).getText();
         System.out.println("middleText = "+middleText);
         driver.switchTo().defaultContent();
         driver.switchTo().frame("frame-top");
         driver.switchTo().frame("frame-left");
         String upperLeftText= driver.findElement(By.tagName("body")).getText();
         System.out.println("upperLeftText = "+upperLeftText);
         driver.switchTo().defaultContent();
         driver.switchTo().frame("frame-bottom");
         String bottomText= driver.findElement(By.xpath("//body")).getText();
         System.out.println("bottomText = "+bottomText);




     }

}
