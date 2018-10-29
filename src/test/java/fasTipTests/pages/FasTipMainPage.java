package fasTipTests.pages;

/**
 * FasTipMainPage.java
 * Purpose: PageObject class for Main Page
 *
 * @author Haris Saleem
 */


import fasTipTests.PageObject;
import fasTipTests.pages.locators.MainPageLocators;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FasTipMainPage extends PageObject {

    public FasTipMainPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBy(id = MainPageLocators.lblTitleId)
    private AndroidElement appTitleLabel;

    @AndroidFindBy(id = MainPageLocators.txtBillAmountId)
    private AndroidElement billAmountTextBox;

    @AndroidFindBy(id = MainPageLocators.btnCalculateTipId)
    private AndroidElement calculateTipButton;

    @AndroidFindBy(id = MainPageLocators.btnSettingsAccId)
    private AndroidElement settingsIcon;

    @AndroidFindBy(id = MainPageLocators.lblTipPctValId)
    private AndroidElement tipPercentLabel;

    @AndroidFindBy(id = MainPageLocators.lblTipAmountId)
    private AndroidElement tipAmountLabel;

    @AndroidFindBy(id = MainPageLocators.lblTotalAmountId)
    private AndroidElement totalAmountLabel;

    public AndroidElement getSettingsIcon() { return settingsIcon; }
    public AndroidElement getAppTitle(){
        return appTitleLabel;
    }
    public AndroidElement getBillAmountTextBox() { return billAmountTextBox; }
    public AndroidElement getCalculateTipButton() { return calculateTipButton; }
    public AndroidElement getTipPercentLabel() { return tipPercentLabel; }
    public AndroidElement getTipAmountLabel() { return tipAmountLabel; }
    public AndroidElement getTotalAmountLabel() { return totalAmountLabel; }


}
