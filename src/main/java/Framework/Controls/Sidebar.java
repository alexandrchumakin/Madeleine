package Framework.Controls;

import Framework.Common.StringHelper;
import Framework.Common.Synchronization;
import Framework.Common.WebDriverHelper;
import org.openqa.selenium.By;

public class Sidebar {
    private final static By byLocator = By.xpath("//div[@id='block-system-main-menu']/div/ul");

    public static void OpenItem(String label) throws Exception {
        try{WebDriverHelper.findElement(byLocator, 5).findElement(By.xpath("//a[contains(text(), '" + label + "')]")).click();}
        catch (Exception ex){throw new Exception(String.format("\r\nDEBUG: Cannot select '%1$s' from sidebar, error: '%2$s'", label, StringHelper.formatMessage(ex)));}
        finally { Synchronization.WaitPageSource(); }
    }

}
