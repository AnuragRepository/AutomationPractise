package Selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class LocatosPrac {

    public static void main(String[] args)
    {
        System.setProperty("wedriver.chrome.drver","");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        //WAP to grab Name title using above above relative locator
        WebElement nameTextBox= driver.findElement(By.xpath("//input[@name='name']"));
        //WebElement nameLabel = driver.findElement(with (By.tagName("label")).above(nameTextBox));
        WebElement nameLabel = driver.findElement(with (By.tagName("label")).above(By.xpath("//input[@name='name']")));
        System.out.println("nameLabel = "+nameLabel.getText());
        Assert.assertEquals(nameLabel.getText(),"Name");

    /*    WAP to enter some date input in DOB using below relative locator -
            cannot do due to flex element limitation, do submit*/

        WebElement DOBLabel = driver.findElement(By.xpath("//label[@for='dateofBirth']"));
        WebElement submit =driver.findElement(with(By.tagName("input")).below(DOBLabel));
        submit.click();
        String submitMessage = driver.findElement(By.xpath("//*[@class='alert alert-success alert-dismissible']")).getText();
        System.out.println("submitMessage =  "+submitMessage);
        Assert.assertEquals(submitMessage.split("×\n")[1],"Success! The Form has been submitted successfully!.");

        //WAP to select checkbox of some specified title, use toLeftOf relative locator

        WebElement labelText= driver.findElement(By.xpath("//*[text()='Check me out if you Love IceCreams!']"));
        WebElement checkbox=driver.findElement(with (By.tagName("input")).toLeftOf(labelText));
        Assert.assertFalse(checkbox.isSelected());
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());

        //WAP to find of dynamiclabel of radio button, use toRightOf relative locator
        WebElement radioOption1 = driver.findElement(By.xpath("//*[@id='inlineRadio1']"));
        WebElement labelRadio = driver.findElement( with (By.tagName("label")).toRightOf(radioOption1));
        System.out.println("labelRadio = "+labelRadio.getText());
        Assert.assertEquals(labelRadio.getText(),"Student");

    }
}
