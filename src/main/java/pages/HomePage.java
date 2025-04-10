package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import static utils.BaseTest.*;

public class HomePage {


    // Locators
    private By signup = By.xpath("//a[text()='Create an Account']");
    //private By signin = By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]']");
    private By cookieAcceptButton = By.xpath("//button[span[text()='AGREE']]");
    private By elemsignin = By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/']");

    // Handle cookies
    public void handleCookies() {
        try {
            getDriver().manage().window().maximize();
            WebElement acceptCookies = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(cookieAcceptButton));
            Assert.assertNotNull(acceptCookies, "Cookie accept button is not found.");
            getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(true);", acceptCookies);
            acceptCookies.click();
            System.out.println("Cookies were handled successfully.");
        } catch (Exception e) {
            Assert.fail("Failed to handle cookies: " + e.getMessage());
        }
    }




    // Click the clicksignupButton
    public void clicksignupButton() {
        try {
            WebElement signupBtn = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(signup));
            Assert.assertNotNull(signupBtn, "CreateAccount button is not clickable.");
            signupBtn.click();
            System.out.println("CreateAccount button clicked successfully.");
        } catch (Exception e) {
            Assert.fail("Failed to click the CreateAccount button: " + e.getMessage());
        }
    }
    // Click the clicksignIpButton
    public void clicksignInButton() {
        try {
            WebElement SignInButton = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elemsignin));
            Assert.assertNotNull(SignInButton, "SignInButton  is not clickable.");
            SignInButton.click();
            System.out.println("SignInButton button clicked successfully.");
        } catch (Exception e) {
            Assert.fail("Failed to click the SignInButton button: " + e.getMessage());
        }
    }

}

