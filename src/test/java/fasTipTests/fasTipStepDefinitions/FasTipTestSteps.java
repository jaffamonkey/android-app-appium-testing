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
import java.text.ParseException;

public class FasTipTestSteps extends BaseTest {

    private FasTipMainPage fasTipMainPage = new FasTipMainPage(driver);
    private FasTipSettingsPage fasTipSettingsPage = new FasTipSettingsPage(driver);

    // Static values to share among steps for calculation
    private static Double expectedTipPercentage = 15.0;
    private static Double expectedTipAmount = 0.00;


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
        Double tipPercentValueDbl = Double.parseDouble(tipPercentValue);
        fasTipSettingsPage.getSaveTipPercentageButton().click();
        expectedTipPercentage = tipPercentValueDbl;
    }

    @Then("^Validate that \"([^\"]*)\" has correct value against provided ([^\"]*)$")
    public void validateThatHasCorrectValue(String valueField, Double billValue) {

        AndroidElement valueLabel;

        Double expectedTotalBillValue;

        switch (valueField) {
            case "Tip Percentage":
                // Get value from UI
                valueLabel = fasTipMainPage.getTipPercentLabel();
                // Remove % sign to get numeric value
                Double actualTipPercentageDbl = Double.parseDouble(valueLabel.getText().replaceAll("%",""));
                // Format percentage value to 2 digits for comparison
                String actualTipPercentage = String.format("%.2f", actualTipPercentageDbl);
                String expectedTipPercentageFormatted = String.format("%.2f", expectedTipPercentage);
                // Perform assertion
                softly.assertThat(actualTipPercentage)
                        .isEqualTo(expectedTipPercentageFormatted);
                break;
            case "Tip Amount":
                // Get value from UI
                valueLabel = fasTipMainPage.getTipAmountLabel();
                // Calculate Amount using formula Bill Value * Tip Percentage / 100
                expectedTipAmount = billValue * expectedTipPercentage / 100;
                // Remove $ sign and get numeric value for assertion
                String actualTipAmount = valueLabel.getText().replaceAll("\\$","");
                Double actualTipAmountValue = Double.valueOf(actualTipAmount);
                // Perform assertion
                softly.assertThat(String.format("%.2f",actualTipAmountValue))
                        .isEqualTo(String.format("%.2f",expectedTipAmount));
                break;
            case "Total Amount":
                // Get value from UI
                valueLabel = fasTipMainPage.getTotalAmountLabel();
                // Remove $ sign to get numeric value
                String actualTotalAmount = valueLabel.getText().replaceAll("\\$","");
                // Calculate total bull using formula: Total Bill = Bill Value + Tip Amount calculated earlier
                expectedTotalBillValue = billValue + expectedTipAmount;
                softly.assertThat(actualTotalAmount)
                        .isEqualTo(String.format("%.2f",expectedTotalBillValue));
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
