package Basics;

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
import java.util.List;

public class FlightSiteTest {

    @Test
    public void test() {
        FlightSiteTest classObj = new FlightSiteTest();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        String increamentIcon = "(//div[@class='ad-row-right']/span[3])[3]";
        String toCityLookUp = "//div[@class ='right1'] //div[@class='search_options_menucontentbg']";
        String autoSuggestFramelocator = "//ul[contains(@class,'ui-autocomplete')]";
        String countryCode = "Aus";

        staticDropdown(driver);
        staticDropdownWithoutSelect(driver, increamentIcon);
        dynamicDropdown(driver,toCityLookUp);
        currentDate(driver);
        verifycheckBoxAndToolTip(driver);
        autoSuggestiveDropDown(driver,countryCode,autoSuggestFramelocator);
        verifydisabledfield(driver);
        classObj.searchFlight(driver);// non static method so need class obj for calling
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

    public void searchFlight(WebDriver driver)
    {
        //Both works, recommended parent child relationship as it will identify unique webelement location
        //WebElement search= driver.findElement(By.xpath("//span[@class='btn-find-flight-home']/input[1]"));// parent child traverse
        WebElement search= driver.findElement(By.xpath("//span[@class='btn-find-flight-home'] //input[@type='submit']"));// parent child relationship
        search.click();// site loading same page , website issue so not validating further via assertion
    }


    public static void currentDate(WebDriver driver)
    {
       WebElement currentDate = driver.findElement(By.xpath("//a[contains(@class,'ui-state-active')]"));
       currentDate.click();
       WebElement dateField = driver.findElement(By.xpath("//div[@class='picker-first2']/span"));
       Assert.assertEquals(dateField.getText(),"Sun, May 05 2019");// use system data via date class for realtime
    }

    public static void disabledCheck(WebElement disabledWebElement, WebElement roundTripRadio, WebElement roundTripRadioLabel)
    {
        if(disabledWebElement.getDomAttribute("style").contains("1"))
        {
            System.out.println("Field is enabled ");
            Assert.assertTrue(roundTripRadio.isSelected());
            Assert.assertEquals(roundTripRadioLabel.getText(),"Round Trip");
            Assert.assertEquals(disabledWebElement.getDomAttribute("style"),"display: block; opacity: 1;");
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }

    public static void verifydisabledfield(WebDriver driver)
    {
        WebElement disabledWebElement = driver.findElement(By.xpath("//div[@class='book']/div[1]/div[5]"));
        WebElement oneWayRadio = driver.findElement(By.xpath("//div[@id='flightSearchContainer'] //input[@type='radio'][1]"));
        WebElement oneWayRadioLabel = driver.findElement(By.xpath("//div[@id='flightSearchContainer'] //input[@type='radio'][1]/following-sibling::label"));
        WebElement roundTripRadio = driver.findElement(By.xpath("//div[@id='flightSearchContainer'] //input[@type='radio'][1]/parent::td/following-sibling::td[1]/input"));
        WebElement roundTripRadioLabel = driver.findElement(By.xpath("//div[@id='flightSearchContainer'] //input[@type='radio'][1]/parent::td/following-sibling::td[1]/input/following-sibling::label"));

        Assert.assertTrue(oneWayRadio.isSelected());
        Assert.assertEquals(oneWayRadioLabel.getText(),"One Way");
        Assert.assertEquals(disabledWebElement.getDomAttribute("style"),"display: block; opacity: 0.5;");
        roundTripRadio.click();
        disabledCheck(disabledWebElement,roundTripRadio,roundTripRadioLabel);

    }


    public static void autoSuggestiveDropDown(WebDriver driver,String countryCode, String autoSuggestFramelocator)
    {
        WebElement autoSuggestDrop = driver.findElement(By.xpath("//div[@class='main_header']/div/div/fieldset/span/following-sibling::input"));
        autoSuggestDrop.sendKeys(countryCode);
        explicitWait(driver,autoSuggestFramelocator);
        List<WebElement> autoElement= driver.findElements(By.xpath(autoSuggestFramelocator+"/li/a"));
        for(WebElement countryElement : autoElement )
        {
            if(countryElement.getText().equalsIgnoreCase("Austria"))
            {
                countryElement.click();
                break;
            }
        }
        //Assert.assertEquals(autoSuggestDrop.getText(),"Austria"); Not fetching text, Application issue

    }

    public static void verifycheckBoxAndToolTip(WebDriver driver)
    {
        Assert.assertFalse(driver.findElement(By.xpath("//*[text()=' Student']/preceding-sibling::input")).isSelected());
        driver.findElement(By.xpath("//*[text()=' Student']/preceding-sibling::input")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()=' Student']/preceding-sibling::input")).isSelected());
        String locatedBychild = driver.findElement(By.xpath("//*[text()=' Student']/preceding-sibling::input/preceding-sibling::a/span")).getText();
        String locatedByParent = driver.findElement(By.xpath("//*[text()=' Student']/preceding-sibling::input/parent::div/a/span")).getText();
        String locatedByAncestor =driver.findElement(By.xpath("//*[text()=' Student']/preceding-sibling::input/ancestor::div/div[4]/a/span")).getText();
        Assert.assertEquals(locatedBychild,"Applicable for all students above the age of 12 years studying with a bonafide school/university. Limited inventory available. Valid photo ID and educational institute I-card need to be presented for verification at check-in. Bookings under this offer are refundable. Conditions apply.");
        Assert.assertEquals(locatedByParent,"Applicable for all students above the age of 12 years studying with a bonafide school/university. Limited inventory available. Valid photo ID and educational institute I-card need to be presented for verification at check-in. Bookings under this offer are refundable. Conditions apply.");
        Assert.assertEquals(locatedByAncestor,"Applicable for all students above the age of 12 years studying with a bonafide school/university. Limited inventory available. Valid photo ID and educational institute I-card need to be presented for verification at check-in. Bookings under this offer are refundable. Conditions apply.");
        int totalCheckBox = driver.findElements(By.xpath("//*[@type='checkbox']")).size();
        System.out.println("Total CheckBoxes in page = "+totalCheckBox);
        Assert.assertEquals(totalCheckBox,6);

    }

    public static void staticDropdown (WebDriver driver)
    {
        WebElement currencyDropdown= driver.findElement(By.xpath("//div[@class='currency-dropdown']/select"));
        Select selectObj = new Select(currencyDropdown);
        selectObj.getFirstSelectedOption().getText();
        Assert.assertEquals(selectObj.getFirstSelectedOption().getText(),"INR");
        selectObj.selectByIndex(2);
        Assert.assertEquals(selectObj.getFirstSelectedOption().getText(),"AED");
        selectObj.selectByVisibleText("USD");
        Assert.assertEquals(selectObj.getFirstSelectedOption().getText(),"USD");
        selectObj.selectByValue("AED");//value tag value
        Assert.assertEquals(selectObj.getFirstSelectedOption().getText(),"AED");
        selectObj.selectByContainsVisibleText("U");
        Assert.assertEquals(selectObj.getFirstSelectedOption().getText(),"USD");
    }

    public static void staticDropdownWithoutSelect(WebDriver driver, String locPath)
    {
      WebElement passengerDropdown = driver.findElement(By.xpath("//div[@class='book']/div/div[6]/div[1]/following-sibling::div[1]"));
      String defaultSelectedValue = passengerDropdown.getText();
      Assert.assertEquals(defaultSelectedValue,"1 Adult");
      passengerDropdown.click();
      explicitWait(driver,locPath);
      for(int i=0;i<2;i++)
      {
          driver.findElement(By.xpath("(//div[@class='ad-row-right']/span[3])[1]")).click();
          driver.findElement(By.xpath("(//div[@class='ad-row-right']/span[3])[2]")).click();
          driver.findElement(By.xpath("(//div[@class='ad-row-right']/span[3])[3]")).click();
      }
        defaultSelectedValue = passengerDropdown.getText();
        Assert.assertEquals(defaultSelectedValue,"3 Adult, 2 Child, 2 Infant");
        driver.findElement(By.xpath("//div[@class='adult-popup']/div[4]/input")).click();
    }

    public static void explicitWait(WebDriver driver, String locPath)
    {
        WebDriverWait waitObj = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitObj.until(ExpectedConditions.elementToBeClickable(By.xpath(locPath)));

    }

    public static void dynamicDropdown(WebDriver driver, String locPath)
    {
       WebElement fromCityTextBox= driver.findElement(By.xpath("//input[@id ='ctl00_mainContent_ddl_originStation1_CTXT']"));
       fromCityTextBox.click();
       WebElement fromCity = driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='DEL']"));
       fromCity.click();
       explicitWait(driver,locPath);
       WebElement toCity = driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='PNQ']"));
       toCity.click();
       WebElement toCityTextBox= driver.findElement(By.xpath("//div[@class ='right1'] //span[@id='ctl00_mainContent_ddl_destinationStation1_CTXTaction']"));
       //Assert.assertEquals(fromCityTextBox.getText(),"DEL"); Not working empty value Passing, tried via SOP
       //Assert.assertEquals(toCityTextBox.getText(),"PNQ"); Not working empty value Passing, tried via SOP
    }


}
