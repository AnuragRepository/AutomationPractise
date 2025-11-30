package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ForgotPasswordTest {

    static String username = "anurag";
    static String password = "Password";
    static String errorMessage = "";
    static String phonenumber = "9898989898";
    static String passwordLine = "";

    public static void explicitWait(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@class='form']/h1")));
    }

    public static void verifylogin(WebDriver driver)
    {
        driver.findElement(By.id("inputUsername")).sendKeys(username);
        driver.findElement(By.name("inputPassword")).sendKeys(password);
        explicitWait(driver);
        driver.findElement((By.className("signInBtn"))).click();
    }

    public static void verifysuccessloggedMessage(WebDriver driver)
    {
        String actualMsg = driver.findElement(By.xpath("//div[@class='login-container']/p")).getText();
        System.out.println("Successful Login message = "+actualMsg);
        Assert.assertEquals(actualMsg,"You are successfully logged in.");
        String actualLoggedName = driver.findElement(By.xpath("//div[@class='login-container']/h2")).getText();
        System.out.println("Logged In User Text = "+actualLoggedName);
        String actualNameWithComma=actualLoggedName.split(" ")[1];
        String actualLoggedInUserName=actualNameWithComma.split(",")[0];
        System.out.println("UserName from Logged In User Text  = "+actualLoggedInUserName);
        Assert.assertEquals(actualLoggedInUserName, username);
    }

    public static void clickforgotPassword(WebDriver driver)
    {

        driver.findElement(By.linkText("Forgot your password?")).click();
        driver.findElement(By.xpath("//div[contains(@class,'form-container')]/form/input[1]")).sendKeys(username);
        driver.findElement(By.xpath("//div[contains(@class,'sign-up-container')]/form/input[2]")).sendKeys(password);
        driver.findElement(By.xpath("//div[contains(@class,'form-container sign-up-container')]/form/input[3]")).sendKeys(phonenumber);
        driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[2]")).click();
        passwordLine = driver.findElement(By.xpath("//p[@class='infoMsg']")).getText();
        System.out.println("Text Consisting of Password = "+passwordLine);
        String arr1[]=(passwordLine.split("'"));
        String arr2[] = arr1[1].split("'");
        password= arr2[0];
        System.out.println("Fetched Password from Text = "+password);
        driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
    }
    public static void logedOut(WebDriver driver)
    {
        driver.findElement(By.xpath("//button[text()='Log Out']")).click();
    }

    public static void loggedOut(WebDriver driver)
    {
        driver.findElement(By.xpath("//button[text()='Log Out']")).click();
    }

    public static void verifyerrorloggedMessage(WebDriver driver)
    {
        errorMessage =driver.findElement(By.xpath("//p[@class ='error']")).getText();
        Assert.assertEquals(errorMessage,"* Incorrect username or password");
        System.out.println("Error message = "+errorMessage);
    }

    public static void verifyloginHomePage(WebDriver driver)
    {
        String actualSignInText=driver.findElement(By.xpath("//form[@class='form']/h1")).getText();
        Assert.assertEquals(actualSignInText,"Sign in");
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.close();
    }

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        verifyloginHomePage(driver);
        verifylogin(driver);
        verifyerrorloggedMessage(driver);
        clickforgotPassword(driver);
        explicitWait(driver);
        verifylogin(driver);
        verifysuccessloggedMessage(driver);
        loggedOut(driver);
        verifyloginHomePage(driver);
        closeBrowser(driver);

    }
}
