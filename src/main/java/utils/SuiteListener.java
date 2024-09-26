package utils;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SuiteListener implements ITestListener, IAnnotationTransformer {
//    ExtentReports report = BaseTest.getExtentReport();
//    ExtentTest eTest;
//
//    public void onTestStart(ITestResult result) {
//        String testName = result.getName();
//        eTest = report.createTest(testName);
//        eTest.log(Status.INFO,testName+" execution started...");
//    }
//
//    public void onTestSuccess(ITestResult result) {
//        String testName = result.getName();
//        eTest = report.createTest(testName);
//        eTest.log(Status.PASS,testName+" execution started...");
//    }

    public void onTestFailure(ITestResult result) {
        String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String fileName = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod().getMethodName() + "-" + dateName + ".png";
        File file1 = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file1, new File(fileName ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
