package Assignments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment5 {

    @Test
    public void test() {
        /*a. Select any checkBox
        b. Grab the text of selected checkbox
        c. select option in dropdown which is text/label of selected checkbox
        d. Enter the label text in popup textbox
        e. verify the pop message contains checkbox label or not*/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/#");
        WebElement checkBox2 = driver.findElement(By.xpath("//*[@id='checkBoxOption2']"));
        Assert.assertFalse(checkBox2.isSelected());
        checkBox2.click();
        Assert.assertTrue(checkBox2.isSelected());
        //WebElement checkboxLabel = checkBox2.findElement(By.xpath("//parent::label"));
        String selectedcheckBoxLabel = driver.findElement(By.xpath("//*[text()='Option2']")).getText();
        Assert.assertEquals(selectedcheckBoxLabel,"Option2");
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
