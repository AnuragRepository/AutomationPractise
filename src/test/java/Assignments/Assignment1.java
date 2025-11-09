package Assignments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Assignment1 {

    @Test public void test() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().window().maximize();
        String successMessageLocator = "//div[contains(@class,'alert-success')]";
        enterName(driver);
        enterEmail(driver);
        enterPassword(driver);
        checkCheckbox(driver);
        selectGender(driver);
        selectRadio(driver);
        enterCurrentDate(driver);
        clickSubmit(driver);
        verifySuccessMessage(driver,successMessageLocator);
        closeBrowser(driver);
        getName();
    }

    public void getName()

    {
        System.out.println("My name is anurag");
        System.out.println("My name is anurag");
        System.out.println("My name is anurag");
        System.out.println("My name is anurag");
        System.out.println("My name is anurag");
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }


    public static void explicitWait(WebDriver driver, String successMessageLocator)
    {
        WebDriverWait expWaitObj= new WebDriverWait(driver, Duration.ofSeconds(5));
        expWaitObj.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(successMessageLocator)));

    }

    public static void enterName(WebDriver driver)
    {
        WebElement Name = driver.findElement(By.xpath("//input[@name='name']"));
        Name.sendKeys("Guitar");
        //Assert.assertEquals(Name.getText(),"Guitar"); get text not working for this application
    }
    public static void enterEmail(WebDriver driver)
    {
        WebElement email = driver.findElement(By.xpath("//input[@name='email']"));
        email.sendKeys("Guitar@gmail.com");
        //Assert.assertEquals(email.getText(),"Guitar@gmail.com");
    }
    public static void enterPassword(WebDriver driver)
    {
        WebElement password = driver.findElement(By.xpath("//input[@id='exampleInputPassword1']"));
        password.sendKeys("Password");
        //Assert.assertEquals(password.getText(),"Password");
    }
    public static void checkCheckbox(WebDriver driver)
    {
        WebElement checkBox = driver.findElement(By.xpath("//input[@class='form-check-input']"));
        checkBox.click();
        Assert.assertTrue(checkBox.isSelected());
    }
    public static void selectGender(WebDriver driver)
    {
        WebElement checkBox = driver.findElement(By.xpath("//select[@id='exampleFormControlSelect1']"));
        Select selectObj = new Select(checkBox);
        selectObj.selectByVisibleText("Female");
        Assert.assertEquals(selectObj.getFirstSelectedOption().getText(),"Female");
    }
    public static void selectRadio(WebDriver driver)
    {
        WebElement studentRadio = driver.findElement(By.xpath("//input[@id='inlineRadio1']"));
        studentRadio.click();
        Assert.assertTrue(studentRadio.isSelected());
    }
    public static void enterCurrentDate(WebDriver driver)
    {
        WebElement DOB = driver.findElement(By.xpath("//input[@name='bday']"));
        DOB.sendKeys("3000-12-31");
        //Assert.assertEquals(DOB.getText(),"3000-12-31");
    }
    public static void clickSubmit(WebDriver driver)
    {
        WebElement submit = driver.findElement(By.xpath("//input[@value='Submit']"));
        submit.click();
    }
    public static void verifySuccessMessage(WebDriver driver,String successMessageLocator)
    {
        explicitWait(driver,successMessageLocator);
        WebElement successMessage = driver.findElement(By.xpath(successMessageLocator));
        System.out.println("zero index = "+successMessage.getText().split("×")[0]);
        System.out.println("first index = "+successMessage.getText().split("×")[1].trim());
        Assert.assertEquals(successMessage.getText().split("×")[1].trim(),"Success! The Form has been submitted successfully!.");
    }



}
