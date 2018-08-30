package Tests;

import Framework.Common.*;
import Framework.Controls.Sidebar;
import Framework.interfaces.IClickable;
import Framework.interfaces.IControl;
import Framework.interfaces.IReadOnly;
import Framework.interfaces.IValuable;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.File;
import java.util.Map;


public class BaseDefinition {
    static int count = 0;
    static String folderName;

    @Before
    public void createConn() throws Exception{
        count++;
        Driver.initDriver();
        RepositoryParser.ParseXML();
    }

    @After
    public void delConn(Scenario scenario) throws Exception {
       if(scenario.isFailed()) {
           String absPath = new File("TestResults").getAbsoluteFile().toString();
           folderName = (folderName == null) ? absPath + "\\" + StringHelper.getCurrentDate() : folderName;
           Driver.takeScreenShot(folderName+ "\\" + count + ".png" );
       }
       Driver.closeDriver();
    }

    @Given("^I am logged into site$")
    public void I_am_logged_into_site() throws Throwable {
        LoginPage.Login();
    }

    @Given("^I am logged into site \"([^\"]*)\"$")
    public void I_am_logged_into_site_URL(String URL) throws Throwable {
        LoginPage.Login(URL);
    }

    @When("^I am on page \"([^\"]*)\"$")
    public void I_am_on_page(String pageLabel) throws Throwable {
        Page.currentPageLabel = pageLabel;
        Synchronization.WaitPageSource();
    }

    @When("^I click on \"([^\"]*)\"$")
    public void I_click_on(String controlLabel) throws Throwable {
        IClickable clickableElement = Factory.CreateControl(controlLabel);
        clickableElement.click();
    }


    @When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
    public void I_fill_in_with(String controlLabel, String value) throws Throwable {
        IValuable field = Factory.CreateControl(controlLabel);
        field.setValue(StringHelper.parseValue(value));
    }

    @When("^I populate form with$")
    public void I_populate_form_with(DataTable tableData) throws Throwable {
        TableHelper table = new TableHelper(tableData.asMaps());
        for (Map<String, String> aTableData : table.tableData) {
            for(int i = 0; i < aTableData.size(); i++) {
                IValuable field = Factory.CreateControl(aTableData.keySet().toArray()[i].toString());
                field.setValue(aTableData.values().toArray()[i].toString());
            }
        }
    }

    @When("^I select \"([^\"]*)\" from sidebar$")
    public void I_select_from_sidebar(String item) throws Throwable {
        Sidebar.OpenItem(item);
    }

    @Then("^I should see \"([^\"]*)\" (enabled|disabled)$")
    public void I_should_see_enabled(String controlLabel, String enOrDis) throws Throwable {
        IControl control = Factory.CreateControl(controlLabel);
        Assert.assertTrue(String.format("Control '%1$s' is not enabled.", controlLabel), enOrDis.equals("enabled") ? control.isEnabled() : !control.isEnabled() );
    }

    @Then("^I should see form with$")
    public void I_should_see_form_with(DataTable tableData) throws Throwable {
        String resErr = "";
        TableHelper table = new TableHelper(tableData.asMaps());
        for (Map<String, String> aTableData : table.tableData) {
            for(int i = 0; i < aTableData.size(); i++) {
                String controlLabel = aTableData.keySet().toArray()[i].toString();
                IReadOnly field = Factory.CreateControl(controlLabel);
                String expValue = aTableData.values().toArray()[i].toString();
                String actValue = field.getValue();
                if(!actValue.equals(expValue))
                    resErr+=String.format("\r\n DEBUG: Expected value for control '%1$s' is '%2$s', but actual got '%3$s'",controlLabel, expValue, actValue);
            }
        }
        Assert.assertTrue(resErr, resErr.isEmpty());
    }

    @Then("^I should see \"([^\"]*)\" with \"([^\"]*)\"$")
    public void I_should_see_with(String controlLabel, String expValue) throws Throwable {
        IReadOnly field = Factory.CreateControl(controlLabel);
        String actValue = field.getValue();
        expValue = StringHelper.parseValue(expValue.replace("\\n", "\n"));
        Assert.assertTrue(String.format("\r\nDEBUG: actual value is: '%1$s', but actual got '%2$s'",actValue, expValue), actValue.contains(expValue));
    }

    @Then("^I should see \"([^\"]*)\" highlighted$")
    public void I_should_see_highlighted(String controlLabel) throws Throwable {
        IValuable field = Factory.CreateControl(controlLabel);
        Assert.assertTrue(field.isEnabled());
    }

}
