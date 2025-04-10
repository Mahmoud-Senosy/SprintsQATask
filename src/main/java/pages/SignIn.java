package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import utils.UIActions;

import static utils.BaseTest.getWebDriverWait;

public class SignIn {
    private static By eleSignEmail = By.id("email");
    private static By eleSingPassword = By.id("pass");
    private static By eleSingIn = By.id("send2");




    public void accountSignIn(String Email, String password){
        UIActions uiActions = new UIActions();
        uiActions.clearAndText(eleSignEmail,Email);
        uiActions.clearAndText(eleSingPassword,password);
        WebElement SignButton = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(eleSingIn));
        SignButton.click();
    }

    public void accountSignInSignOut(String EmailOut, String passwordOut){
        UIActions uiActions = new UIActions();
        uiActions.clearAndText(eleSignEmail,EmailOut);
        uiActions.clearAndText(eleSingPassword,passwordOut);
        WebElement SignButton = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(eleSingIn));
        SignButton.click();
    }

}
