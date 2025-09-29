package Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Assignment5 {

    public static void main(String[] args)
    {
        /*a. Select any checkBox
        b. Grab the text of selected checkbox
        c. select option in dropdown which is text/label of selected checkbox
        d. Enter the label text in popup textbox
        e. verify the pop message contains checkbox label or not*/

        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/#");
        WebElement checkBox2 = driver.findElement(By.id("checkBoxOption2"));
        Assert.assertFalse(checkBox2.isSelected());
        checkBox2.click();
        Assert.assertTrue(checkBox2.isSelected());
        String selectedcheckBoxLabel = checkBox2.findElement(By.xpath("//parent::label")).getText();
        Assert.assertEquals(selectedcheckBoxLabel,"Option2");

    }

}
