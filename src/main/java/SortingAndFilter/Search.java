package SortingAndFilter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Search {
    public static void main(String[] args)
    {
        System.setProperty("wedriver.chrome.drver","");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        String searchText = "APPLE";
        WebElement searchTextBox= driver.findElement(By.xpath("//*[contains(@id, 'search')]"));
        searchTextBox.sendKeys(searchText);
        Assert.assertEquals(searchTextBox.getDomAttribute("value"),searchText);
        List<WebElement> webElementsList= driver.findElements(By.xpath("//tbody/tr/td[1]"));
        List<String> itemList = webElementsList.stream().map(s->s.getText().toUpperCase()).filter(s->s.contains(searchText)).collect(Collectors.toList());
        Assert.assertEquals(webElementsList.size(),itemList.size());

       // webElementsList.stream().map(s->s.getText()).forEach(s->System.out.print(s+" "));
       // List<String> textList = webElementsList.stream().map(s->s.getText()).collect(Collectors.toList());

      /*  String searchText1 = "Apple";
        List <WebElement> itemList1 = webElementsList.stream().filter(s->s.getText().contains(searchText1)).collect(Collectors.toList());
        Assert.assertEquals(webElementsList.size(),itemList1.size());

        java.lang.AssertionError: expected [1] but found [2] in line 34
        since streams are case sensitive and 33 line gives output 1 only i.e Apple excluding Pineapple*/

       // if used getText map change the list type like weblement to string but filter doesnot








    }

}
