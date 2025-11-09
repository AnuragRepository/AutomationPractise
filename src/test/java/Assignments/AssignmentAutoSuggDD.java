package Assignments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class AssignmentAutoSuggDD {
    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        //System.out.print("Enter countryInitials = ");
        Scanner sc = new Scanner(System.in);
       // String countryInitials =  sc.nextLine();
        String countryInitials ="uni";// since we are running test in jenkins, user input disabled
        driver.findElement(By.xpath("//*[@id='autocomplete']")).sendKeys(countryInitials);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='ui-id-1']"))));
        List<WebElement> countriesOption = driver.findElements(By.xpath("//*[@id='ui-id-1']/li/div"));
        for(int i =0; i<countriesOption.size();i++)
        {
          if(countriesOption.get(i).getText().contains("United Arab"))
          {
              countriesOption.get(i).click();
          }
        }
       // System.out.println(driver.findElement(By.xpath("//*[@id='autocomplete']")).getDomAttribute("value"));
        //Assert.assertEquals(countriesOption.get(1).getText(),"United Arab Emirates");

        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
