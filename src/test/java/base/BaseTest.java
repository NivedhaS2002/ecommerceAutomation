package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    public WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    // ─── Extent Report Setup (runs once before all tests) ───────────────────
    @BeforeSuite
    public void setupReport() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        ExtentSparkReporter spark = new ExtentSparkReporter(
                "reports/TestReport_" + timestamp + ".html");
        spark.config().setDocumentTitle("E-Commerce Automation Report");
        spark.config().setReportName("SauceDemo Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Your Name");
        extent.setSystemInfo("Application", "SauceDemo E-Commerce");
        extent.setSystemInfo("Environment", "QA");
    }

    // ─── Browser Setup (runs before each test method) ───────────────────────
    @BeforeMethod
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");  // Uncomment to run without browser window
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(
                java.time.Duration.ofSeconds(10));
    }

    // ─── Screenshot Helper ───────────────────────────────────────────────────
    public String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir")
                + "/screenshots/" + testName + "_" + timestamp + ".png";
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    // ─── After Each Test: log pass/fail + screenshot on failure ─────────────
    @AfterMethod
    public void afterTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test FAILED: " + result.getThrowable());
            String path = takeScreenshot(result.getName());
            test.addScreenCaptureFromPath(path, "Failure Screenshot");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test PASSED");
        } else {
            test.log(Status.SKIP, "Test SKIPPED");
        }
        driver.quit();
    }

    // ─── Flush Report (runs once after all tests) ───────────────────────────
    @AfterSuite
    public void teardownReport() {
        extent.flush();
        System.out.println("✅ Extent Report generated in /reports folder.");
    }
}
