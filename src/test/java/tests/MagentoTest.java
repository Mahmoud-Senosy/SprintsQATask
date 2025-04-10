package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;
import utils.BaseTest;





public class MagentoTest extends BaseTest {

    @Test(dataProvider = "SignInData")
    public void signIn(String email, String password){
        ExtentTest test = getExtentTest();
        test.info("Starting the Sign In test: navigating to the login page, entering valid user credentials, and verifying successful login by checking for account dashboard visibility.");
        HomePage homePage = new HomePage();
        SignIn signIn = new SignIn();
        homePage.clicksignInButton();
        signIn.accountSignIn(email, password);
    }

    @Test
    public void createNewAccount() {

        ExtentTest test = getExtentTest();
        test.info("Starting the Sign Up test: navigating to the registration page, entering unique user information, submitting the form, and verifying that the new account is created and redirected to the user dashboard.");
        HomePage homePage = new HomePage();
        CreateAccount createAccount = new CreateAccount();

        homePage.clicksignupButton();
        createAccount.enterUserDetails();
        createAccount.isAccountCreated();

    }
    @Test(dataProvider = "SignOutData")
    public void signOut(String email, String password) {

        ExtentTest test = getExtentTest();
        test.info("Starting the Log Out test: logging in with a valid user, initiating the logout process from the account menu, and verifying that the user is redirected and logged out successfully.");
        HomePage homePage = new HomePage();
        CreateAccount createAccount = new CreateAccount();
        SignIn signIn = new SignIn();

        homePage.clicksignInButton();
        signIn.accountSignInSignOut(email, password);
        createAccount.signOutFromAccount();

    }

    @Test(dataProvider = "SearchData")
    public void searchOnItem(String email, String password) {

        ExtentTest test = getExtentTest();
        test.info("Starting the product search test: searching for the keyword 'bag', validating that the search results page is displayed, and confirming that the returned product titles or descriptions are relevant to the keyword.");
        HomePage homePage = new HomePage();
        SignIn signIn = new SignIn();
        HomePagedLogged homePagedLogged = new HomePagedLogged();


        homePage.clicksignInButton();
        signIn.accountSignIn(email, password);
        homePagedLogged.serchOnItem();
    }

    @Test
    public void completeOrder() {
        ExtentTest test = getExtentTest(); // Get the current thread's ExtentTest
        test.info("Starting the Complete Order test: executing the full e-commerce workflow from login, searching and selecting a product, adding it to the cart, providing shipping and payment details, placing the order, and verifying the order confirmation message.");

        HomePage homePage = new HomePage();
        CreateAccount createAccount = new CreateAccount();
        HomePagedLogged homePagedLogged = new HomePagedLogged();
        Checkout checkout = new Checkout();
        Sale sale = new Sale();

        homePage.clicksignupButton();
        createAccount.enterUserDetails();
        createAccount.isAccountCreated();
        homePagedLogged.clickSaleButton();
        sale.addFirstItemSearch();
        sale.proceedPayment();
        checkout.shippingAddress();
        checkout.clickOnNext();
        checkout.placeOrderButton();

    }
}
