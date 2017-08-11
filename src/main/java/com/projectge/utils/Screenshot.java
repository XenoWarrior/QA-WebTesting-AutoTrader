package com.projectge.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class Screenshot {

    public static String take(WebDriver webDriver, String fileName) throws IOException {
        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + File.separatorChar + "Report" + File.separatorChar + "Screenshots" + File.separatorChar + fileName +".jpg";
        FileUtils.copyFile(scrFile, new File(filePath));
        
        System.out.println("File Saved at: " + filePath);
        
        return filePath;
    }

	public static void capture(ExtentTest t, WebDriver d, String fn) {
		try {
			t.addScreenCaptureFromPath(Screenshot.take(d, fn));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
