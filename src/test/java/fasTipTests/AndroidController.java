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

public class AndroidController {

    private AndroidController() {
    }

    private static DesiredCapabilities capabilities = new DesiredCapabilities();
    private static AndroidDriver<AndroidElement> driver;


    public static AndroidDriver<AndroidElement> prepareAndroidForAppium(boolean isAvdApp, String appName, String serverAddress, String serverPort) {

        //mandatory capabilities
        if (driver == null) {
            setCommonCapabilities();
            if (isAvdApp) {
                setCapabilitiesForAvdApp(appName);
            } else {
                setCapabilitiesForRealApp();
            }
            try {
                driver = new AndroidDriver<>(new URL("http://" + serverAddress + ":" + serverPort + "/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        } else return driver;
    }

    private static void setCapabilitiesForAvdApp(String appName) {
        File appDir = new File("src/test/resources/apps/");
        File app = new File(appDir, appName);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5X");
        //other caps
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability("appPackage", "org.traeg.fastip");
        capabilities.setCapability("appActivity", "org.traeg.fastip.MainActivity");
    }

    private static void setCapabilitiesForRealApp() {
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
        capabilities.setCapability("appPackage", "ae.propertyfinder.propertyfinder");
        capabilities.setCapability("appActivity", "ae.propertyfinder.consumer.ui.activity.ConfigurationActivity");
    }

    private static void setCommonCapabilities() {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
    }
}
