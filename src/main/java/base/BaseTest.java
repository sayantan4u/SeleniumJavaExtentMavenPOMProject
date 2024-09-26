package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Constants;

import java.io.File;
//import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
    public static WebDriver driver;
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public static ExtentTest logger;


    @BeforeTest
    public void startExtentReport(){
        //ExtentReports(String filePath,Boolean replaceExisting)
        //filepath - path of the file, in .htm or .html format - path where your report needs to generate.
        //replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
        //True (default): the file will be replaced with brand-new markup, and all existing data will be lost. Use this option to create a brand new report
        //False: existing data will remain, new tests will be appended to the existing report. If the supplied path does not exist, a new file will be created.
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "SayantanQE");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        //setExtentReport(extent);
        sparkReporter.config().setTheme(Theme.DARK);
        extent.setSystemInfo("Host Name", "My Host");
        extent.setSystemInfo("Environment", "Regression Testing");
        extent.setSystemInfo("User Name", "Sayantan Dutta");
        //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test Results by Sayantan");
    }

//    public static ExtentReports getExtentReport() {
//        //ExtentReports(String filePath,Boolean replaceExisting)
//        //filepath - path of the file, in .htm or .html format - path where your report needs to generate.
//        //replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
//        //True (default): the file will be replaced with brand-new markup, and all existing data will be lost. Use this option to create a brand new report
//        //False: existing data will remain, new tests will be appended to the existing report. If the supplied path does not exist, a new file will be created.
//        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "SayantanQE");
//        extent = new ExtentReports();
//        extent.attachReporter(sparkReporter);
//        setExtentReport(extent);
//        sparkReporter.config().setTheme(Theme.DARK);
//        extent.setSystemInfo("Host Name", "My Host");
//        extent.setSystemInfo("Environment", "Regression Testing");
//        extent.setSystemInfo("User Name", "Sayantan Dutta");
//        //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
//        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
//        sparkReporter.config().setDocumentTitle("Automation Report");
//        sparkReporter.config().setReportName("Automation Test Results by Sayantan");
//        return extent;
//    }

//    public void setExtentReport(ExtentReports ext) {
//        extent = ext;
//    }

//    //This method is to capture the screenshot and return the path of the screenshot.
//    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
//        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//        String destinationFileName = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + screenshotName + "-" + dateName + ".png";
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        File finalDestination = new File(destinationFileName);
//        try {
//            FileUtils.copyFile(source, finalDestination);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return destinationFileName;
//    }

    @BeforeMethod
    @Parameters("browser")
    public void beforeMethodMethod(String browser, Method testMethod){
        logger = extent.createTest(testMethod.getName());
        setupDriver(browser);
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @AfterMethod
    public void getResult(ITestResult result) {
        String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.
            //String screenshotPath = getScreenshot(driver, result.getMethod().getMethodName());
            String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod().getMethodName() + "-" + dateName + ".png";

            //To add it in the extent report
            //logger.fail("Test Case failed check screenshot: " + logger.addScreenCaptureFromPath(screenshotPath));
            logger.fail("Test case failed! lease check screenshot: " + logger.addScreenCaptureFromPath(screenshotPath));
        } else if (result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS){
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
        }
        driver.quit();
    }
    @AfterTest
    public void endReport(){
        // writing everything to document
        //flush() - to write or update test information to your report.
        extent.flush();
    }

    public void setupDriver(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            // Create an instance of ChromeOptions
            ChromeOptions options = new ChromeOptions();
            //options.setCapability("browserVersion", "126.0.6478.164");
            //options.setBinary("/usr/local/bin/chromedriver");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.chromiumdriver().setup();
            // Create an instance of FirefoxOptions
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("chromium")){
            WebDriverManager.chromedriver().setup();
            // Create an instance of ChromeOptions
            ChromeOptions options = new ChromeOptions();
            //options.setCapability("browserVersion", "126.0.6478.164");
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
            options.setBinary("/usr/bin/chromium-browser");
            //options.setBinary("/usr/bin/chromedriver");
            //options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-debugging-port=9222");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            // Create an instance of FirefoxOptions
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new EdgeDriver(options);
        }
    }
}