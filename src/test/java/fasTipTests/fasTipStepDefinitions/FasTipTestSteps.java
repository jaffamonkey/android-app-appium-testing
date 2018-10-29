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
import io.appium.java_client.android.AndroidElement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class FasTipTestSteps extends BaseTest {

    private FasTipMainPage fasTipMainPage = new FasTipMainPage(driver);

    private static Double expectedTipAmount = 0.00;
    private static Double expectedTipPercentage = 15.0;
    private static Double expectedTotalBillValue = 0.00;


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
    public void tipPercentageIsSetAsConfigured() {

    }

    @Then("^Validate that \"([^\"]*)\" has correct value against provided ([^\"]*)$")
    public void validateThatHasCorrectValue(String valueField, Double billValue) throws ParseException {
        AndroidElement valueLabel;
        DecimalFormat twoDecimalFormat = new DecimalFormat("#0.00");
        DecimalFormat oneDecimalFormat = new DecimalFormat("#0.0");
        NumberFormat format = NumberFormat.getCurrencyInstance();

        switch (valueField) {
            case "Tip Percentage":
                valueLabel = fasTipMainPage.getTipPercentLabel();
                String actualTipPercentage =valueLabel.getText();
                softly.assertThat(actualTipPercentage).isEqualTo(oneDecimalFormat.format(expectedTipPercentage) + "%");
                break;
            case "Tip Amount":
                valueLabel = fasTipMainPage.getTipAmountLabel();
                expectedTipAmount = billValue * 15 / 100;
                String actualTipAmount = valueLabel.getText();
                softly.assertThat(actualTipAmount).isEqualTo("$" + twoDecimalFormat.format(expectedTipAmount));
                break;
            case "Total Amount":
                valueLabel = fasTipMainPage.getTotalAmountLabel();
                String actualTotalAmount = valueLabel.getText();
                expectedTotalBillValue = billValue + expectedTipAmount;
                softly.assertThat(actualTotalAmount).isEqualTo("$" + twoDecimalFormat.format(expectedTotalBillValue));
                break;
        }

        softly.assertAll();
    }
}
