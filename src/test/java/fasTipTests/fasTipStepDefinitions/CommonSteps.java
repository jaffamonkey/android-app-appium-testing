package fasTipTests.fasTipStepDefinitions;

/**
 * CommonSteps.java
 * Purpose: Common steps to be used by different step definition classes
 * or those steps that do not belong to a particular step definition class
 *
 * @author Haris Saleem
 */

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import fasTipTests.AndroidController;
import fasTipTests.BaseTest;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CommonSteps extends BaseTest {

    public CommonSteps(){
        super();
    }

    @Before
    public void initDriver(){
        String appName = propertyValues.getString("app_name");
        String serverAddress = propertyValues.getString("server_address");
        String serverPort = propertyValues.getString("server_port");
        driver = AndroidController.prepareAndroidForAppium(appName, serverAddress, serverPort);
    }

    @After("@CustomPercentage")
    public void resetApp(){
        driver.resetApp();
    }


    @And("^\"([^\"]*)\" key is pressed$")
    public void keyIsPressed(String key) {

        switch (key) {
            case "Back":
                driver.pressKey(new KeyEvent(AndroidKey.BACK));
            case "Home":
                System.out.println(new KeyEvent(AndroidKey.HOME));
        }
    }
}
