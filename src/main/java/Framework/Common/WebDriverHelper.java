package Framework.Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelper {
    public static void waitFor(By byLocator, int seconds ){
        try{
            WebDriverWait wait = new WebDriverWait(Driver.CurrentDriver, seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        } catch  (Exception ignored) { }
    }

    public static void waitFor(By byLocator ){
        waitFor(byLocator, 10);
    }

    public static WebElement findElement(By by, int timeoutInSeconds){
        WebDriverWait waiter = new WebDriverWait(Driver.CurrentDriver, timeoutInSeconds);
        waiter.until( ExpectedConditions.presenceOfElementLocated(by) );
        WebElement element = null;
        try{
            element = Driver.CurrentDriver.findElement(by);
            element.sendKeys("");    // try to make focus
        }catch(Exception ex){
            System.out.println(String.format("\r\nDEBUG: Cannot set focus to element with '%1$s' locator, error: '%2$s'", by, StringHelper.formatMessage(ex)));
        }
        return element;
    }
}
