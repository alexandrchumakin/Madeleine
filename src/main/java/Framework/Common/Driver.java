package Framework.Common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class Driver {
    public static WebDriver CurrentDriver;

    public static void initDriver(){
        String browserType = Configurations.GetValueByKey("browser").toLowerCase();
        if (browserType.equals("chrome")) {
//            System.setProperty("webdriver.chrome.driver", ".\\add\\chromedriver.exe");
            CurrentDriver = new ChromeDriver();
            CurrentDriver.manage().window().maximize();

        } else if (browserType.equals("ff") || browserType.equals("firefox")) {
            CurrentDriver = new FirefoxDriver();
            CurrentDriver.manage().window().maximize();

        } else {
            System.out.println("\r\nWARNING: You set 'ie' as default browser!");
            CurrentDriver = new InternetExplorerDriver();
        }
    }

    public static void closeDriver(){
        CurrentDriver.close();
    }

    public static void takeScreenShot(String path){
        File scrFile = ((TakesScreenshot)CurrentDriver).getScreenshotAs(OutputType.FILE);
        try {FileUtils.copyFile(scrFile, new File(path));}
        catch (Exception ex){System.out.println(String.format("\r\nDEBUG: Cannot save screen shot by path '%1$s' , error: '%2$s'", path, StringHelper.formatMessage(ex)));}
    }
}
