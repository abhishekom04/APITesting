package api.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenshots {

    WebDriver driver = new ChromeDriver();

    public void takeScreenshot(){

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String timeStamp = sdf.format(date);
        File srcFile  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        new File(System.getProperty("user.dir")+File.separator+"Screenshots\\").mkdir();
        String targetFile = System.getProperty("user.dir")+File.separator+"Screenshots"+File.separator+timeStamp;
        try {
            FileUtils.copyFile(srcFile, new File(targetFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
