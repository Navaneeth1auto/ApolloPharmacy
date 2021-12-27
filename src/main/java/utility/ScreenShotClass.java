package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotClass {
	WebDriver driver;
	File srcScreenShot;
	File dstScreenShot;
	TakesScreenshot screenShot;
	public ScreenShotClass(WebDriver driver) {
		this.driver=driver;
	}
	
	public String takeScreenshot(String name) {
		screenShot=(TakesScreenshot)driver;
		srcScreenShot=screenShot.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/ScreenShots/"+name+".png";
		dstScreenShot=new File(path);
		try {
			FileUtils.copyFile(srcScreenShot, dstScreenShot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
