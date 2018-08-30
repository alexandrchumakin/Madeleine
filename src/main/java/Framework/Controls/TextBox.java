package Framework.Controls;

import Framework.Common.WebDriverHelper;
import Framework.interfaces.IValuable;
import org.openqa.selenium.WebElement;

public class TextBox extends BaseControl implements IValuable {

    public TextBox(String pageName, String controlLabel){
        super(pageName, controlLabel);
    }

    public void setValue(String value) {
        WebElement field = WebDriverHelper.findElement(byLocator, 5);
        field.clear();
        field.sendKeys(value);
    }

    public String getValue() {
        return WebDriverHelper.findElement(byLocator, 5).getText().trim();
    }

    public boolean isHighlighted(){
        return WebDriverHelper.findElement(byLocator, 5).getAttribute("class").contains("error");
    }
}
