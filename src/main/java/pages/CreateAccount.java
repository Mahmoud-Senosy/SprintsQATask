package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import utils.DataGenerator;
import utils.UIActions;

import static utils.BaseTest.getWebDriverWait;


public class CreateAccount {
    private String generatedEmail;
    private String generatedPassword;
    // Locators
    private By eleFirstname = By.id("firstname");
    private By eleLastname = By.id("lastname");
    private By eleEmail = By.id("email_address");
    private By elePassword = By.id("password");
    private By eleConfirmPassword = By.id("password-confirmation");
    private By eleCreateAccount = By.xpath("//button[@type='submit' and contains(@class, 'primary')]");
    private By eleSuccessMessage = By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    private By elemDropdownButton= By.cssSelector("button.action.switch[data-action='customer-menu-toggle']");
    private By elemSignOut =  By.xpath("//a[contains(text(), 'Sign Out')]");


    /** Method to enter user details
     * To add the account details
     */
    public void enterUserDetails() {
        DataGenerator dataGenerator = new DataGenerator();
        UIActions uiActions = new UIActions();
        // Enter Account Details
        uiActions.clearAndText(eleFirstname,dataGenerator.generateName());
        uiActions.clearAndText(eleLastname,dataGenerator.generateName());

        generatedEmail = dataGenerator.generateEmail();
        generatedPassword = dataGenerator.generatePassword();

        uiActions.clearAndText(eleEmail,generatedEmail);
        uiActions.clearAndText(elePassword,generatedPassword);
        uiActions.clearAndText(eleConfirmPassword,generatedPassword);
        WebElement createAccountButton = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(eleCreateAccount));
        createAccountButton.click();
    }

    /**Verify if account is created successfully
     *
     */

    public void isAccountCreated() {
        WebElement confirmationMessage = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(eleSuccessMessage));
        Assert.assertTrue(confirmationMessage.getText().contains("Thank you for registering with Main Website Store."));
    }

    public void signOutFromAccount() {

        WebElement dropdownButton = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elemDropdownButton));
        dropdownButton.click();
        WebElement signOut = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elemSignOut));
        signOut.click();
    }



}

