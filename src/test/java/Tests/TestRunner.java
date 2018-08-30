package Tests;

import org.junit.runner.RunWith;

import cucumber.api.junit.*;

@RunWith(Cucumber.class)
@Cucumber.Options(format={"pretty", "html:target/cucumber"})
public class TestRunner {}
