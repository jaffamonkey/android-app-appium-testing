package fasTipTests.pages;

/**
 * FasTipSettingsPage.java
 * Purpose: PageObject class for Settings Page
 *
 * @author Haris Saleem
 */

import fasTipTests.PageObject;
import fasTipTests.pages.locators.SettingsPageLocators;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FasTipSettingsPage extends PageObject {
    public FasTipSettingsPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBy(id = SettingsPageLocators.txtTipPercentageSettingId)
    private AndroidElement tipPercentageSettingsTextbox;

    @AndroidFindBy(id = SettingsPageLocators.btnSaveTipPercentageSettingId)
    private AndroidElement saveTipPercentageButton;

    public AndroidElement getTipPercentageSettingsTextbox() { return tipPercentageSettingsTextbox; }

    public AndroidElement getSaveTipPercentageButton() { return saveTipPercentageButton; }
}
