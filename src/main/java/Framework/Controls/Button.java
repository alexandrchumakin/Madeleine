package Framework.Controls;

import Framework.Common.Synchronization;
import Framework.Common.WebDriverHelper;
import Framework.interfaces.IClickable;

public class Button extends BaseControl implements IClickable {

    public Button(String pageName, String controlLabel){
        super(pageName, controlLabel);
    }

    public void click() {
        WebDriverHelper.findElement(byLocator, 5).click();
        Synchronization.WaitPageSource();
//        Driver.CurrentDriver.findElement(byLocator).click();
    }

}
