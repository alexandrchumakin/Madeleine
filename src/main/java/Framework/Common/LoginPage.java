package Framework.Common;

import Framework.interfaces.IClickable;
import Framework.interfaces.IValuable;

public class LoginPage {
    public static void Login() throws Exception {
        String URL = Configurations.GetValueByKey("URL");
        Login(URL);
    }

    public static void Login(String URL) throws Exception{
        Driver.CurrentDriver.navigate().to(URL);
        Page.currentPageLabel = "Login";
        IValuable loginUserField = Factory.CreateControl("UserName");
        loginUserField.setValue(Configurations.GetValueByKey("loginUser"));
        IValuable loginPW = Factory.CreateControl("Password");
        loginPW.setValue(Configurations.GetValueByKey("loginPW"));
        IClickable loginButton = Factory.CreateControl("LoginButton");
        loginButton.click();
        Synchronization.WaitPageSource();
    }

}
