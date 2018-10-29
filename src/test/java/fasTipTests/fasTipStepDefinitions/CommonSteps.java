package fasTipTests.fasTipStepDefinitions;

/**
 * CommonSteps.java
 * Purpose: Common steps to be used by different step definition classes
 * or those steps that do not belong to a particular step definition class
 *
 * @author Haris Saleem
 */

import cucumber.api.java.Before;
import fasTipTests.AndroidController;
import fasTipTests.BaseTest;

public class CommonSteps extends BaseTest {

    public CommonSteps(){
        super();
    }

    @Before
    public void initDriver(){
        String appName = propertyValues.getString("app_name");
        String serverAddress = propertyValues.getString("server_address");
        String serverPort = propertyValues.getString("server_port");
        driver = AndroidController.prepareAndroidForAppium(true, appName, serverAddress, serverPort);
    }
}
