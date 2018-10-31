package fasTipTests;

/**
 * PageObject.java
 * Purpose: PageObject base class to implement pageFactory
 *
 * @author Haris Saleem
 */

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class PageObject {

    public PageObject(AndroidDriver<AndroidElement> driver) {
        driver = BaseTest.driver;
        Duration duration = Duration.ofSeconds(5);

        PageFactory.initElements(new AppiumFieldDecorator(driver, duration), this);
    }

}
