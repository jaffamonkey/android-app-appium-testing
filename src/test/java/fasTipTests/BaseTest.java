package fasTipTests;

/**
 * BaseTest.java
 * Purpose: Test base class handle externalization of values, manage driver object and quit driver after execution.
 *
 * @author Haris Saleem
 */


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.assertj.core.api.SoftAssertions;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class BaseTest {

    protected static AndroidDriver<AndroidElement> driver;
    protected static ResourceBundle propertyValues;
    protected SoftAssertions softly = new SoftAssertions();
    protected DecimalFormat twoDecimalFormat;

    public BaseTest() {
        Locale locale = new Locale("en", "US");
        propertyValues = ResourceBundle.getBundle("config", locale);
    }

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            try {
                driver.quit();
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }
    };

    //static block to initialize driver as soon as tests are fired from Runner Class
    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }


}
