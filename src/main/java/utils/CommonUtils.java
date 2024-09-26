package utils;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils extends BaseTest{

//    public static String captureScreenshot(String fileName){
//        String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        String finalFileName = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + fileName + "-" + dateName + ".png";
//        File sourceFileName = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(sourceFileName, new File(finalFileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return finalFileName;
//    }
}
