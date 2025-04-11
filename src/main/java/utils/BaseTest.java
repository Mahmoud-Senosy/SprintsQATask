package utils;

import ExcelUtils.ExcelUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BaseTest {
    public ExtentReports extent;


    //private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static WebDriver getDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();

            // ABSOLUTE MINIMUM CONFIG THAT WORKS IN AZURE
            options.addArguments(
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--headless=new",
                    "--remote-allow-origins=*"
            );

            // CRITICAL - THIS FIXES USER-DATA-DIR ISSUE
            options.addArguments("--incognito");
            options.setExperimentalOption("useAutomationExtension", false);

            // NUCLEAR OPTION - FORCE CLEAN START
            try {
                Runtime.getRuntime().exec("pkill -9 -f chrome");
                Runtime.getRuntime().exec("pkill -9 -f chromedriver");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Cleanup warning: " + e.getMessage());
            }

            driver.set(new ChromeDriver(options));
        }
        return driver.get();
    }


    private static void cleanAzureEnvironment() {
        try {
            // Kill all Chrome processes
            Runtime.getRuntime().exec("pkill -9 -f chrome");
            Runtime.getRuntime().exec("pkill -9 -f chromedriver");

            // Clean temp directories
            Runtime.getRuntime().exec("rm -rf /tmp/.com.google.Chrome*");
            Runtime.getRuntime().exec("rm -rf /tmp/chrome-profile*");
            Runtime.getRuntime().exec("rm -rf /home/runner/.config/google-chrome");

            Thread.sleep(3000); // Wait for cleanup
        } catch (Exception e) {
            System.err.println("Azure cleanup failed: " + e.getMessage());
        }
    }

    private static void forceCleanup() {
        try {
            // Linux-specific cleanup (for Azure)
            Runtime.getRuntime().exec("pkill -9 -f chrome");
            Runtime.getRuntime().exec("pkill -9 -f chromedriver");
            Thread.sleep(2000); // Wait for cleanup
        } catch (Exception e) {
            System.err.println("Cleanup failed: " + e.getMessage());
        }
    }
    private static void killAllChromeProcesses() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                Runtime.getRuntime().exec("pkill -9 -f chrome");
                Runtime.getRuntime().exec("pkill -9 -f chromedriver");
            } else if (os.contains("win")) {
                Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
                Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
            }
            Thread.sleep(2000); // Wait for processes to terminate
        } catch (Exception e) {
            System.err.println("Error killing processes: " + e.getMessage());
        }
    }
    private static void cleanupChromeProcesses() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("linux") || os.contains("mac")) {
                Runtime.getRuntime().exec("pkill -f chrome");
                Runtime.getRuntime().exec("pkill -f chromedriver");
            } else if (os.contains("win")) {
                Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
                Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
            }
            Thread.sleep(1000); // Wait for processes to terminate
        } catch (Exception e) {
            System.err.println("Error cleaning up processes: " + e.getMessage());
        }
    }
    public static void setDriver(WebDriver driver) {
        BaseTest.driver.set(driver);
    }

    public static JavascriptExecutor getJavascriptExecutor() {
        return javascriptExecutor.get();
    }

    public static void setJavascriptExecutor(JavascriptExecutor javascriptExecutor) {
        BaseTest.javascriptExecutor.set(javascriptExecutor);
    }

    public static WebDriverWait getWebDriverWait() {
        return webDriverWait.get();
    }

    public static void setWebDriverWait(WebDriverWait webDriverWait) {
        BaseTest.webDriverWait.set(webDriverWait);
    }
    public static ExtentTest getExtentTest(){
        return extentTest.get() ;
    }

    public static void setExtentTest(ExtentTest extentTest) {
        BaseTest.extentTest.set(extentTest);
    }
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<JavascriptExecutor>  javascriptExecutor = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> webDriverWait = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest>    extentTest    = new ThreadLocal<>();


    public static void setUp(String url) {
        WebDriverManager.chromedriver().setup();
        String userDataDir = System.getProperty("user.dir") + "/target/user-data-dir/" + Thread.currentThread().getId();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-data-dir=" + userDataDir);

        setDriver(new ChromeDriver(options));

        setJavascriptExecutor((JavascriptExecutor) getDriver());
        setWebDriverWait(new WebDriverWait(getDriver(), Duration.ofSeconds(60)));
        getDriver().get(url);
    }



    /**
     * To Close and Remove local Thread
     */

    public static synchronized void tearDown() {

            try {
                driver.get().quit();
            } catch (Exception e) {
                System.out.println("Cleanup error: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    @BeforeTest
    public void setUpExtentReport() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timeStamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("UI Test Results");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester", "Mahmoud Senosy");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
    }


    @BeforeMethod
    public void init(Method method) {
        // Create a new ExtentTest for each test method, making it thread-safe
        setExtentTest(extent.createTest(method.getName()));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
     
        options.addArguments("--user-data-dir=/tmp/chrome_profile_" + UUID.randomUUID());


        // Your custom setup for opening the browser
        setUp("https://magento.softwaretestingboard.com/");
        getDriver().manage().window().maximize();  // Maximize window
    }
    @AfterMethod(alwaysRun = true)
    public void captureResult(ITestResult result) {
        ExtentTest test = getExtentTest(); // Get the current thread's ExtentTest

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("❌ Test Failed: " + result.getName());
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("✅ Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("⚠️ Test Skipped: " + result.getName());
        }
        if (result.getStatus() == ITestResult.FAILURE) {
            saveScreenshot(result.getName(), test);
//            savePageSource();
        }
        tearDown();
    }
    public void saveScreenshot(String testName, ExtentTest test) {
        String base64Screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        test.info("📸 Screenshot for: " + testName,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

    @AfterTest
    public void tearDownReport() {
        extent.flush();
    }


    @DataProvider(name = "SignInData")
    public Object[][] SignInDataProvider() throws IOException {
        String excelPath = System.getProperty("user.dir") + "/resources/testdata.xlsx";
        File excelFile = new File(excelPath);
        if (!excelFile.exists()) {
            throw new FileNotFoundException("Excel file not found at: " + excelPath);
        }
        ExcelUtils excelUtils = new ExcelUtils(excelPath);
        List<String[]> data = excelUtils.getData("Credentials", "SignIn");
        excelUtils.close();
        return data.toArray(new Object[0][]);
    }

    @DataProvider(name = "SignOutData")
    public Object[][] SignOutDataDataProvider() throws IOException {
        String excelPath = System.getProperty("user.dir") + "/resources/testdata.xlsx";
        File excelFile = new File(excelPath);
        if (!excelFile.exists()) {
            throw new FileNotFoundException("Excel file not found at: " + excelPath);
        }
        ExcelUtils excelUtils = new ExcelUtils(excelPath);
        List<String[]> data = excelUtils.getData("Credentials", "SignOut");
        excelUtils.close();
        return data.toArray(new Object[0][]);
    }

    @DataProvider(name = "SearchData")
    public Object[][] SearchDataProvider() throws IOException {
        String excelPath = System.getProperty("user.dir") + "/resources/testdata.xlsx";
        File excelFile = new File(excelPath);
        if (!excelFile.exists()) {
            throw new FileNotFoundException("Excel file not found at: " + excelPath);
        }
        ExcelUtils excelUtils = new ExcelUtils(excelPath);
        List<String[]> data = excelUtils.getData("Credentials", "Search");
        excelUtils.close();
        return data.toArray(new Object[0][]);
    }

}
