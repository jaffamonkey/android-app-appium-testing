package fasTipTests;

/**
 * AndroidController.java
 * Purpose: Android Driver management class that creates driver object and sets capabilities.
 *
 * @author Haris Saleem
 */


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidController extends BaseTest {

    private AndroidController() {
    }

    private static DesiredCapabilities capabilities = new DesiredCapabilities();
    private static AndroidDriver<AndroidElement> driver;


    public static AndroidDriver<AndroidElement> prepareAndroidForAppium(String appName, String serverAddress, String serverPort) {

        //mandatory capabilities
        if (driver == null) {
            setCapabilitiesForApp(appName);

            try {
                driver = new AndroidDriver<>(new URL("http://" + serverAddress + ":" + serverPort + "/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        } else return driver;
    }

    // Capabilities for Emulator
    private static void setCapabilitiesForApp(String appName) {
        File appDir = new File("src/test/resources/apps/");
        File app = new File(appDir, appName);
        String avdName = propertyValues.getString("avd_name");
        capabilities.setCapability("appPackage", propertyValues.getString("app_package_name"));
        capabilities.setCapability("appActivity", propertyValues.getString("app_activity"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, avdName);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
    }
}
