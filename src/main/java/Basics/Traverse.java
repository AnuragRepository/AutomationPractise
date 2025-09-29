package Basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Traverse {

    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        //Given XPath of Login button
       String Login = driver.findElement(By.xpath("//header[contains(@class,'jumbotron')]/div/button[2]")).getText();
       System.out.println("Login button text = "+Login);

        //1. Sibling to downward sibling traverse i.e Traverse to downward signup button from login button

        String signUp = driver.findElement(By.xpath("//header[contains(@class,'jumbotron')]/div/button[2]/following-sibling::button[1]")).getText();
        System.out.println("SignUp button text = "+signUp);

        //2. Sibling to upward sibling traverse i.e Traverse to downward Practice button from login button

        String Practice = driver.findElement(By.xpath("//header[contains(@class,'jumbotron')]/div/button[2]/preceding-sibling::button[1]")).getText();
        System.out.println("Practice button text = "+Practice);

        //3. Sibling to Parent traverse i.e Traverse to upward header from login button

        String headerParent = driver.findElement(By.xpath("//header[contains(@class,'jumbotron')]/div/button[2]/parent::div")).getText();
        System.out.println("Parent Header label = "+headerParent);

        //4. Sibling to ancestor traverse i.e Traverse to upward header from login button

        String ancestor = driver.findElement(By.xpath("//header[contains(@class,'jumbotron')]/div/button[2]/ancestor::header")).getText();
        System.out.println("Ancestor Header label = "+ancestor);



    }


}

