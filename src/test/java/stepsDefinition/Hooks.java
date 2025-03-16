package stepsDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverManager;

public class Hooks extends DriverManager {

    @Before
    public void driverSetUp(){
        setUp();
    }

    @After
    public void quitDriver(){
        tearDown();
    }
}
