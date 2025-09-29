package Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Assignment2 {

    public static void main(String[] args)
    {
        //Login to page using fetched username, password, add all items of page and clicked checkin

        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        String popUpTextLocator = "//div[@id='myModal']/div[1]/div[1]/div[1]/p";
        String checkOutButtonLocator = "//div[contains(@class,'navbar-collapse')]/ul//li[1]/a";
        //login(driver,popUpTextLocator);
        addcartHomePage(driver,checkOutButtonLocator);
    }
    public static void addcartHomePage(WebDriver driver,String checkOutButtonLocator)
    {
        driver.navigate().to("https://rahulshettyacademy.com/angularpractice/shop");//always use driver.get("URL") as it will wait for all webpage element to load
        explicitWait(driver, checkOutButtonLocator);
       List<WebElement> listButton = driver.findElements(By.xpath("//div[@class='col-lg-9']/app-card-list //div[@class='card-footer']/button"));
       List<WebElement> listTitle = driver.findElements(By.xpath("//div[@class='col-lg-9']/app-card-list //div[@class='card-footer']/button/parent::div/preceding-sibling::div[1]/h4/a"));
       List<WebElement> listPrice = driver.findElements(By.xpath("//div[@class='col-lg-9']/app-card-list //div[@class='card-footer']/button/parent::div/preceding-sibling::div[1]/h4/a/parent::h4/following-sibling::h5[1]"));

        for(int i=0;i<listTitle.size();i++)
       {
               for(int j=0;j<listPrice.size();j++)
           {
                   for(int k= 0;k<listButton.size();k++)
               {
                   System.out.println("Items clicked = "+listTitle.get(k).getText()+" of price" +listPrice.get(k).getText());
                   listButton.get(k).click();
               }
           }


       }

    }


    public static void explicitWait(WebDriver driver, String xpathLocation)
    {
        WebDriverWait waitObj = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitObj.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocation)));
    }

    public static void login(WebDriver driver,String popUpTextLocator)
    {
        String text= driver.findElement(By.xpath("//form[@id ='login-form']/div[7]/p")).getText();
        System.out.println("text = "+text);
        String username = text.split("and")[0].split("is")[1].trim();
        String password = text.split("and")[1].split("is")[1].trim().split("\\)")[0];
        System.out.println("username = "+username);
        System.out.println("password = "+password);
        Assert.assertEquals(username,"rahulshettyacademy");
        Assert.assertEquals(password,"learning");
        WebElement usernameTextBox= driver.findElement(By.xpath("//form[@id ='login-form']/div[2]/input"));
        WebElement passwordTextBox= driver.findElement(By.xpath("//form[@id ='login-form']/div[3]/input"));
        usernameTextBox.sendKeys(username);
        passwordTextBox.sendKeys(password);
        WebElement userRadio = driver.findElement(By.xpath("//div[@class='form-check-inline']/label[2]/span[2]/preceding-sibling::input"));
        userRadio.click();
        explicitWait(driver,popUpTextLocator);
        String popUpText= driver.findElement(By.xpath("//div[@id='myModal']/div[1]/div[1]/div[1]/p")).getText();

        //String popUpText= driver.switchTo().alert().getText(); //window pop up not there, HTML object found in DOM of page
        Assert.assertEquals(popUpText,"You will be limited to only fewer functionalities of the app. Proceed?");
        WebElement okayPopUp= driver.findElement(By.xpath("//div[@id='myModal']/div[1]/div[1]/div[1]/p/parent::div/following-sibling::div[1]/button[2]"));
        //driver.switchTo().alert().accept();//window pop up not there, HTML object found in DOM of page
        okayPopUp.click();
        Assert.assertTrue(userRadio.isSelected());
        WebElement dropdown = driver.findElement(By.xpath("//form[@id ='login-form']/div[5]/select"));
        Select selectObj = new Select(dropdown);
        selectObj.selectByValue("consult");
        Assert.assertEquals(selectObj.getFirstSelectedOption().getText(),"Consultant");
        WebElement checkBox = driver.findElement(By.xpath("//form[@id ='login-form']/div[6]/label/span[1]/input"));
        Assert.assertFalse(checkBox.isSelected());
        checkBox.click();
        Assert.assertTrue(checkBox.isSelected());
        WebElement signButton= driver.findElement(By.xpath("//form[@id ='login-form']/div[6]/label/following-sibling::input[1]"));
        signButton.click();

    }



}
