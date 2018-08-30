package Framework.Controls;

import Framework.Common.WebDriverHelper;
import Framework.interfaces.IClickable;

public class CheckBox extends BaseControl implements IClickable {

    public CheckBox(String pageName, String controlLabel){
        super(pageName, controlLabel);
    }

    public void click() {
        WebDriverHelper.findElement(byLocator, 5).click();
    }

}
