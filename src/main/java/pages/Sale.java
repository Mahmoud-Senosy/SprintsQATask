package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.UIActions;

import static utils.BaseTest.getWebDriverWait;

public class Sale {

    private By elemJackets =By.xpath("//a[text()='Jackets' and @href='https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html']\n");
    private By elemSizeL =By.id("option-label-size-143-item-169");
    private By elemBlueColor =By.id("option-label-color-93-item-50");
    private By elemAddToCart =By.xpath("//button[@title='Add to Cart']");
    private By elemCartItem =By.xpath("//a[span[text()='My Cart']]");
    private By elemProceedToCheckOut =By.id("top-cart-btn-checkout");
    private By elemItemNumber =By.xpath("//span[@class='counter qty']");


    public void addFirstItemSearch (){
        UIActions uiActions = new UIActions();

        uiActions.clickOnElement(elemJackets);
        uiActions.clickOnElement(elemSizeL);
        uiActions.clickOnElement(elemBlueColor);
        uiActions.clickOnElement(elemAddToCart);



    }
    public void proceedPayment (){

        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(elemItemNumber));
        WebElement cartItem = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elemCartItem));
        cartItem.click();
        WebElement proceedToCheckOut = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elemProceedToCheckOut));
        proceedToCheckOut.click();
    }

}
