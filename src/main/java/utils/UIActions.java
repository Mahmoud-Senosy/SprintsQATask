package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static utils.BaseTest.getDriver;
import static utils.BaseTest.getWebDriverWait;

public class UIActions {
    public void clearAndText (By element, String text ){

        WebElement firstNameField = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(element));
        firstNameField.clear();
        firstNameField.sendKeys(text);

    }
    public void clickOnElement (By element2 ) {
        WebElement firstNameField2 = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(element2));
        firstNameField2.click();
    }

    public void clearAndTextandEnter (By element, String text ){

        WebElement firstNameField = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(element));
        firstNameField.clear();
        firstNameField.sendKeys(text);
        firstNameField.sendKeys(Keys.RETURN);
    }

    public void clickWithJS(By locator){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        // Wait for element and scroll into view
        WebElement element = getWebDriverWait().until(driver -> {
            WebElement element1 = driver.findElement(locator);
            ((JavascriptExecutor)driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
                    element1
            );
            return element1;
        });
        try{
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            Assert.fail("Failed to click " + element);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
