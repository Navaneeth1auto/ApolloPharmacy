package baseFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	WebDriver driver;
	Properties property;
	org.apache.logging.log4j.Logger log;
	public BaseClass() {
		log= LogManager.getLogger();
		property=new Properties();
		FileInputStream fis=null;
		File file=new File(System.getProperty("user.dir")+"//src//main//resources//config.properties");
		try {
			 fis=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			property.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("logger and properties file configuration is successful");
	}
	
	public void browserSetup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		log.info("Browser setup completed successfully");
	}
	
	public void openApplication() {
		driver.get(property.getProperty("URL"));
		log.info("Application opened successfully");
	}

}
