package Framework.Controls;

import Framework.Common.Page;
import Framework.Common.RepositoryParser;
import Framework.Common.WebDriverHelper;
import Framework.interfaces.IControl;
import org.openqa.selenium.By;

import java.util.HashMap;

public class BaseControl implements IControl {
    protected String controlLabel;
    protected HashMap<String, String> info;
    protected By byLocator;

    public BaseControl(String pageName, String controlLabel){
        Page.currentPageLabel = pageName;
        this.controlLabel = controlLabel;
        info = RepositoryParser.GetControlInfo(pageName, controlLabel);
        genLocator();
    }

    private void genLocator(){
        if(info.containsKey("id"))
            byLocator = By.id(info.get("id"));
        else if(info.containsKey("name"))
            byLocator = By.name(info.get("name"));
        else if(info.containsKey("xpath"))
            byLocator = By.xpath(info.get("xpath"));
        else
            System.out.println(String.format("\r\nDEBUG: Cannot determine locator for control with label '%1$s'.", controlLabel));
    }

    public boolean isEnabled() {
        return WebDriverHelper.findElement(byLocator, 10).isEnabled();
    }
}
