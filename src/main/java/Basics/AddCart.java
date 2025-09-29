package Basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddCart {

    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().window().maximize();
        String vegetablesListLocator = "//h4[@class='product-name']";
        String addToCartButtonLocator = "//div[@class='product-action']/button";
        String cartElements= "//ul[@class='cart-items']";
        String table = "//table[@class='cartTable']";
        String code = "rahulshettyacademy";
        String successcodeappliedmessagePath = "//div[@class='promoWrapper']/span";
        String CheckoutProceedButton= "//button[text()='PROCEED TO CHECKOUT']";

        //Add Tomato, Apple , Nuts Mixture, Cashews, Walnuts
        String [] elementsToBeAdded = {"Tomato", "Apple" , "Nuts Mixture", "Cashews", "Walnuts"};
        List<String> elementsToBeAddedList = Arrays.asList(elementsToBeAdded);
        //addToCartNonOptimizeWay(driver,elementsToBeAddedList, vegetablesListLocator,addToCartButtonLocator);
        addToCartOptimizeWay(driver,vegetablesListLocator,elementsToBeAddedList,addToCartButtonLocator);
        verifyAddedItemsInRequestPage(driver, elementsToBeAdded,cartElements,CheckoutProceedButton);
        proceedCheckout(driver,CheckoutProceedButton);
        verifyAddedItemsInDetailPage(driver,elementsToBeAdded,table);
        applyCoupon(driver,code,successcodeappliedmessagePath);
        verifyDiscountPercentage(driver,code,successcodeappliedmessagePath);
    }
    public static void verifyDiscountPercentage(WebDriver driver,String code, String successcodeappliedmessagePath)
    {
        WebElement discountPercent = driver.findElement(By.xpath("//div[@class='promoWrapper']/following-sibling::b[3]/following-sibling::span[1]"));
        Assert.assertEquals(discountPercent.getText(),"10%");

    }

    public static void applyCoupon (WebDriver driver,String code, String successcodeappliedmessagePath)
    {
        WebElement enterCoupon = driver.findElement(By.xpath("//div[@class='promoWrapper'] //input[@placeholder='Enter promo code']"));
        enterCoupon.sendKeys(code);
        WebElement applyButton= driver.findElement(By.xpath("//button[text()='Apply']"));
        applyButton.click();
        explicitWaitVisibility(driver,successcodeappliedmessagePath);
        WebElement successcodeappliedmessage = driver.findElement(By.xpath(successcodeappliedmessagePath));
        Assert.assertEquals(successcodeappliedmessage.getText(),"Code applied ..!");

    }


    public static void verifyAddedItemsInDetailPage(WebDriver driver, String[] elementsToBeAdded,String table)
    {
        explicitWaitVisibility(driver,table);
        List<WebElement> itemsNameDetails = driver.findElements(By.xpath("//div[@class='products'] //tbody/tr/td[2]/p"));
        for(int i = 0; i<itemsNameDetails.size(); i++)
        {
            String nameDetails= itemsNameDetails.get(i).getText().split(" -")[0];
            if(Arrays.asList(elementsToBeAdded).contains(nameDetails))
            {
                Assert.assertTrue(true);
            }
            else {
                Assert.assertTrue(false);
            }
        }
    }

    public static void proceedCheckout(WebDriver driver,String path)
    {
        WebElement CheckoutProceedButton = driver.findElement(By.xpath(path));
        CheckoutProceedButton.click();
    }



    public static void verifyAddedItemsInRequestPage(WebDriver driver, String[] elementsToBeAdded, String cartElements,String CheckoutProceedButton)
    {
        WebElement cartIcon = driver.findElement(By.xpath("//a[@class='cart-icon']/img"));
        cartIcon.click();
        explicitWaitVisibility(driver,cartElements);
        explicitWaitClickable(driver,CheckoutProceedButton);
        List<WebElement> addedItemsWebElement = driver.findElements(By.xpath("//ul[@class='cart-items'] //li[@class ='cart-item']/div[1]/p[1]"));

        //1st way list with string compare
       for(int i =0; i<addedItemsWebElement.size();i++)
        {
            if (Arrays.asList(elementsToBeAdded).contains(addedItemsWebElement.get(i).getText().split(" -")[0]))
            {
                Assert.assertTrue(true);
            }
        }
        //2nd way- compare Two List
        List<String> addedItems =  new ArrayList<String>();
        for(int i =0; i<addedItemsWebElement.size();i++)
        {
            String textAddedElement=addedItemsWebElement.get(i).getText().split(" -")[0];

            if(!textAddedElement.equalsIgnoreCase(""))
            {
                addedItems.add(textAddedElement);
            }

        }
        System.out.println("Size of added elements "+addedItems.size());
        System.out.println("Size of original elements "+Arrays.asList(elementsToBeAdded).size());
        System.out.print("Added elements are = ");
        for(int i=0;i<addedItems.size();i++)
        {
            System.out.print(addedItems.get(i)+" ");
        }
        System.out.println();
        System.out.print("Original elements are = ");
        for(int i=0;i<(Arrays.asList(elementsToBeAdded)).size();i++)
        {
            System.out.print(Arrays.asList(elementsToBeAdded).get(i)+" ");
        }
        Assert.assertTrue(addedItems.containsAll(Arrays.asList(elementsToBeAdded)));
        Assert.assertTrue((Arrays.asList(elementsToBeAdded).containsAll(addedItems)));
        Assert.assertEquals(addedItems,Arrays.asList(elementsToBeAdded));
        if(addedItems.equals(Arrays.asList(elementsToBeAdded)))
        {
            Assert.assertTrue(true);
        }

        //3rd Method- ArrayEquality
      Assert.assertTrue(Arrays.equals(addedItems.toArray(),elementsToBeAdded));


    }


    public static void explicitWaitVisibility(WebDriver driver, String xpathString)
    {
       WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(20));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathString)));
    }

    public static void explicitWaitClickable(WebDriver driver, String path)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
    }





    public static void addToCartOptimizeWay(WebDriver driver, String vegetablesListLocator, List elementsToBeAddedList, String addToCartButtonLocator)
    {
        int numberOfElementsToBeAdded = 0;
        String text="";
        List <WebElement> vegetablesListName = driver.findElements(By.xpath(vegetablesListLocator));

        for(int i = 0; i<vegetablesListName.size();i++)
        {
            text = vegetablesListName.get(i).getText().split(" -")[0];

            if(elementsToBeAddedList.contains(text))
            {
                driver.findElements(By.xpath(addToCartButtonLocator)).get(i).click();
                if(numberOfElementsToBeAdded == elementsToBeAddedList.size())
                {
                    break;
                }
            }

        }

    }

    public static void addToCartNonOptimizeWay(WebDriver driver, List elementsToBeAddedList, String vegetablesListLocator, String addToCartButtonLocator)
    {
      List <WebElement> vegetablesList = driver.findElements(By.xpath(vegetablesListLocator));
      List <WebElement> addToCartButton = driver.findElements(By.xpath(addToCartButtonLocator));
      for (int i=0;i<vegetablesList.size();i++)
      {
          for(int j=0;j<elementsToBeAddedList.size();j++)
          {
              if(vegetablesList.get(i).getText().split(" -")[0].contains(elementsToBeAddedList.get(j).toString()))
              {
                  System.out.println("ListElements = "+elementsToBeAddedList.get(j));
                  System.out.println(vegetablesList.get(i).getText().split(" -")[0]+" added to Cart");
                  addToCartButton.get(i).click();
              }



          }
      }

    }


}
