package Framework.Controls;

import Framework.Common.Synchronization;
import Framework.Common.WebDriverHelper;
import Framework.interfaces.IClickable;

public class Link extends BaseControl implements IClickable {

    public Link(String pageName, String controlLabel){
        super(pageName, controlLabel);
    }

    public void click() {
        WebDriverHelper.findElement(byLocator, 5).click();
        Synchronization.WaitPageSource();
    }

}
