package utils;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryAnalyzer implements IRetryAnalyzer {
    int count=0;
    int retryCount=1;

    public boolean retry(ITestResult result) {
        while (count < retryCount) {
            count++;
            return true;
        }
        return false;
    }
}
