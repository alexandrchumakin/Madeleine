package Framework.Controls;

import Framework.Common.WebDriverHelper;
import Framework.interfaces.IValuable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;

public class FileUpload extends BaseControl implements IValuable {
    public FileUpload(String pageName, String controlLabel){
        super(pageName, controlLabel);
    }

    public void setValue(String value) {
        WebElement fileInput = WebDriverHelper.findElement(byLocator, 5).findElement(By.xpath("./div[@class='uploader']/input[@type='file']"));
        String imgPath="images\\" + value;
//        File imgFile = new File(".", imgPath);
        File imgFile = new File(imgPath);
//        System.out.println(imgFile.getAbsoluteFile());
        String absPath = imgFile.getAbsoluteFile().toString();
        fileInput.sendKeys(absPath);
    }

    public String getValue() {
        return WebDriverHelper.findElement(byLocator, 5).findElement(By.xpath(".//span[@class='file']/a")).getText();
    }

    public boolean isHighlighted(){
        return false;
    }
}
