package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.DataGenerator;
import utils.UIActions;

import static utils.BaseTest.getWebDriverWait;

public class Checkout {

    private By eleStreetAddress = By.xpath("//input[contains(@class, 'input-text') and @name='street[0]']");
    private By eleCity = By.xpath("//input[@class='input-text' and @name='city']");
    private By eleStateProvince = By.xpath("//select[contains(@class, 'select') and @name='region_id']");
    private By eleZipPostalCode = By.xpath("//input[@class='input-text' and @name='postcode']");
    private By eleCountry = By.xpath("//select[@name='country_id']");
    private By elePhoneNumber = By.xpath("//input[@name='telephone' and @aria-required='true']");
    private By eleradioLocator = By.xpath("//input[@type='radio' and @value='tablerate_bestway']");
    private By eleNext = By.xpath("//span[text()='Next']");
    private By elePlaceOrder = By.xpath("//button[span[text()='Place Order']]");

    /** Enter User details of address for shipment to complete the order
     *
     */
    public void shippingAddress (){

    UIActions uiActions = new UIActions();
    DataGenerator dataGenerator = new DataGenerator();

    //Street Address
    uiActions.clearAndText(eleStreetAddress,dataGenerator.generateName());
    //City Location
    uiActions.clearAndText(eleCity,dataGenerator.generateName());

    //drop down list select state/province
    WebElement selectHawaii  = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(eleStateProvince));
    Select select = new Select(selectHawaii);
    // Select Germany by visible text
    select.selectByVisibleText("Hawaii");

    //ZipCode
    uiActions.clearAndText(eleZipPostalCode,dataGenerator.generateNumber(5));

    //drop down list select Germany element
    WebElement GermCountry  = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(eleCountry));
    // Create Germany from  Select object
    Select selectCountry = new Select(GermCountry);
    // Select Germany by visible text
    selectCountry.selectByVisibleText("United States");

    //Phone Number
    uiActions.clearAndText(elePhoneNumber,dataGenerator.generateMobileNumber());

    // Shipping Method
    uiActions.clickOnElement(eleradioLocator);
}


// click on Next to proceed on your Order

public void clickOnNext(){
    UIActions uiActions = new UIActions();
    uiActions.clickOnElement(eleNext);
    }

 public void placeOrderButton(){
     UIActions uiActions = new UIActions();

     uiActions.clickWithJS(elePlaceOrder);

 }
}
