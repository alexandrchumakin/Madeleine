package Framework.Controls;

import Framework.Common.WebDriverHelper;
import Framework.interfaces.IReadOnly;

public class Message extends BaseControl implements IReadOnly {

    public Message(String pageName, String controlLabel){
        super(pageName, controlLabel);
    }


    public String getValue() {
        return WebDriverHelper.findElement(byLocator, 5).getText().replace("Status message","").replace("Error message","").trim();
    }
}
