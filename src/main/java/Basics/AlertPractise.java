package Basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AlertPractise {

    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        String myName = "Anurag Raj";
        String firstAlertLocatorPath = "//fieldset[@class='pull-right']/input[2]";
        String secondAlertLocatorPath = "//fieldset[@class='pull-right']/input[3]";

        windowAlertPostiveNegativeAcknowledge(driver, firstAlertLocatorPath,secondAlertLocatorPath);
        windowAlertValidateEnteredNameFromAlertText(driver,myName,firstAlertLocatorPath,secondAlertLocatorPath);

    }
    public static void windowAlertPostiveNegativeAcknowledge(WebDriver driver, String firstAlertLocatorPath, String secondAlertLocatorPath )
    {
        WebElement firstAlert = driver.findElement(By.xpath(firstAlertLocatorPath));
        firstAlert.click();
        driver.switchTo().alert().dismiss();
        WebElement secondAlert = driver.findElement(By.xpath(secondAlertLocatorPath));
        secondAlert.click();
        driver.switchTo().alert().accept();
        secondAlert.click();
        driver.switchTo().alert().dismiss();

    }
    public static void windowAlertValidateEnteredNameFromAlertText(WebDriver driver, String myName, String firstAlertLocatorPath, String secondAlertLocatorPath)
    {
        WebElement Name = driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']"));
        Name.sendKeys(myName);
        WebElement FirstAlert = driver.findElement(By.xpath(firstAlertLocatorPath));
        FirstAlert.click();
        String nameFromFirstAlert= driver.switchTo().alert().getText().split(",")[0].split("Hello ")[1];
        Assert.assertEquals(nameFromFirstAlert,myName);
        driver.switchTo().alert().accept();
        Name.sendKeys(myName);
        WebElement secondAlert = driver.findElement(By.xpath(secondAlertLocatorPath));
        secondAlert.click();
        String nameFromSecondAlert = driver.switchTo().alert().getText().split(",")[0].split("Hello ")[1];
        Assert.assertEquals(nameFromSecondAlert,myName);
        driver.switchTo().alert().dismiss();

    }



}
