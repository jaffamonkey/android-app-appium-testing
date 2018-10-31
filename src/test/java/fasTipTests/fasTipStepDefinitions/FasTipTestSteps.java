package fasTipTests.fasTipStepDefinitions;

/**
 * FasTipTestSteps.java
 * Purpose: Step definition class to define all step definitions.
 * Since this is a simple app, a single class and single feature was enough.
 *
 * @author Haris Saleem
 */

import cucumber.api.java.en.*;
import fasTipTests.BaseTest;
import fasTipTests.pages.FasTipMainPage;
import fasTipTests.pages.FasTipSettingsPage;
import io.appium.java_client.android.AndroidElement;
import java.text.DecimalFormat;

public class FasTipTestSteps extends BaseTest {

    private FasTipMainPage fasTipMainPage = new FasTipMainPage(driver);
    private FasTipSettingsPage fasTipSettingsPage = new FasTipSettingsPage(driver);

    private static Double expectedTipPercentage = 15.00;
    private static Double expectedTipAmount = 0.00;
    private DecimalFormat twoDecimalFormat = new DecimalFormat("#0.00");


    @Given("^FasTip app Bill Calculator page is open$")
    public void fastipAppBillCalculatorPageIsOpen() {

        AndroidElement appTitleElement = fasTipMainPage.getAppTitle();
        String actualTitle = appTitleElement.getText();
        softly.assertThat(actualTitle).isEqualTo("FasTip");
        softly.assertAll();
    }

    @When("^([^\"]*) value is entered in the bill amount box with ([^\"]*)$")
    public void valueIsEnteredInTheBillAmountBox(String type, String value) {
        System.out.println("Entering " + type + " value.");
        AndroidElement billAmountTextBox = fasTipMainPage.getBillAmountTextBox();
        billAmountTextBox.clear();
        billAmountTextBox.sendKeys(value);
    }

    @And("^\"([^\"]*)\" button is pressed$")
    public void buttonIsPressed(String buttonName) {
        AndroidElement button = new AndroidElement();
        switch (buttonName) {
            case "Calculate Tip":
                button = fasTipMainPage.getCalculateTipButton();
                break;
            case "Settings":
                button = fasTipMainPage.getSettingsIcon();
                break;
        }

        button.click();
    }

    @And("^Tip percentage is set as ([^\"]*)$")
    public void tipPercentageIsSetAsConfigured(String tipPercentValue) {
        AndroidElement tipTextbox = fasTipSettingsPage.getTipPercentageSettingsTextbox();
        tipTextbox.clear();
        tipTextbox.sendKeys(tipPercentValue);
        fasTipSettingsPage.getSaveTipPercentageButton().click();
        expectedTipPercentage = Double.valueOf(tipPercentValue);
    }

    @Then("^Validate that \"([^\"]*)\" has correct value against provided ([^\"]*)$")
    public void validateThatHasCorrectValue(String valueField, Double billValue) {

        AndroidElement valueLabel;

        Double expectedTotalBillValue;

        switch (valueField) {
            case "Tip Percentage":
                valueLabel = fasTipMainPage.getTipPercentLabel();
                String actualTipPercentage =valueLabel.getText().replaceAll("%","");
                softly.assertThat(twoDecimalFormat.format(Double.valueOf(actualTipPercentage)))
                        .isEqualTo(twoDecimalFormat.format(expectedTipPercentage));
                break;
            case "Tip Amount":
                valueLabel = fasTipMainPage.getTipAmountLabel();
                expectedTipAmount = billValue * expectedTipPercentage / 100;
                String actualTipAmount = valueLabel.getText().replaceAll("\\$","");
                softly.assertThat(twoDecimalFormat.format(Double.valueOf(actualTipAmount)))
                        .isEqualTo(twoDecimalFormat.format(expectedTipAmount));
                break;
            case "Total Amount":
                valueLabel = fasTipMainPage.getTotalAmountLabel();
                String actualTotalAmount = valueLabel.getText().replaceAll("\\$","");
                expectedTotalBillValue = billValue + expectedTipAmount;
                softly.assertThat(twoDecimalFormat.format(Double.valueOf(actualTotalAmount)))
                        .isEqualTo(twoDecimalFormat.format(expectedTotalBillValue));
                break;
        }

        softly.assertAll();
    }

    @When("^Settings page is opened$")
    public void settingsPageIsOpened() {
        AndroidElement settingsIcon = fasTipMainPage.getSettingsIcon();
        settingsIcon.click();
    }

    @Then("^Validate that Tip Percentage is updated on Main screen with ([^\"]*)$")
    public void validateThatIsUpdatedOnMainScreen(String expectedPercentValue) throws Throwable {
        AndroidElement tipPercentageValue = fasTipMainPage.getTipPercentLabel();
        softly.assertThat(tipPercentageValue.getText()).isEqualTo(expectedPercentValue + "%");
    }

    @And("^Tip percentage value is noted$")
    public void tipPercentageValueIsNoted() {
        AndroidElement tipTextbox = fasTipSettingsPage.getTipPercentageSettingsTextbox();
        expectedTipPercentage = Double.valueOf(tipTextbox.getText());
    }

}
