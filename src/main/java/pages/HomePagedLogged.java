package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.UIActions;

import java.util.List;

import static utils.BaseTest.*;

public class HomePagedLogged {

    private By elemSale = By.id("ui-id-8");
    private By elemSearch = By.id("search");


    public void clickSaleButton (){
        UIActions uiActions = new UIActions();

        uiActions.clickOnElement(elemSale);

       // WebElement acceptCookies = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(elemSale));
       //acceptCookies.click();
    }

    public void serchOnItem(){
        UIActions uiActions = new UIActions();

        uiActions.clearAndTextandEnter(elemSearch,"Bag");

        // Verify search results are displayed

        List<WebElement> productTitles = getDriver().findElements(By.cssSelector(".product-item-name a"));;
        Assert.assertTrue(productTitles.size() > 0, "No search results found!");

        // Verify all results contain 'Bag' in the title
        for (WebElement title : productTitles) {
            String productName = title.getText().toLowerCase();
            System.out.println("Product Found: " + productName);
            Assert.assertTrue(productName.contains("bag"), "Irrelevant search result found!");
        }

    }

}
